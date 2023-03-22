<template>
  <div class="quiz-container" ref="QuizContainerRef" :style="{ maxHeight: containerMaxHeight }">
    <el-skeleton :throttle=50 :loading="loading" animated>
      <template #template>
        <el-skeleton-item 
          variant="rect" 
          class="row" 
          v-for="n in Math.floor((containerHeight - 20) / 49)" 
        />
      </template>
      <template #default>
        <el-table-v2
          :columns="columns"
          :data="filteredData || data"
          :width="containerWidth - 40"
          :height="containerHeight - 20"
        />
      </template>
    </el-skeleton>
    <transition name="quiz-toolbar">
      <div class="quiz-toolbar" v-show="selectedItems.length">
        <el-button :icon="Promotion" @click="pushSelected">推送</el-button>
        <el-button :icon="DeleteFilled" @click="deleteSeleted">删除</el-button>
      </div>
    </transition>
  </div>
  <teleport to='body'>
      <suspense>
        <Editboard
          v-model="showEditModal" 
          :editItem="newItem || data[currentIndex]"
          @add-item="onAddItem"
        />
      </suspense>
  </teleport>
</template>

<script setup lang="tsx">
import { ref, unref, watch, onMounted, computed } from 'vue';
import { useDebounceFn } from '@vueuse/core';
import { 
  ElMessage,
  ElCheckbox,
  ElTag,
  ElButton,
  ElInput,
  ElIcon
} from 'element-plus';
import { Promotion, DeleteFilled, Search } from '@element-plus/icons-vue';
import { getAllQuizs, deleteQuizItem, batchDeleteQuizItems } from '../../hooks';
import Editboard from './Editboard.vue';

import type { FunctionalComponent } from 'vue';
import type { Column, CheckboxValueType } from 'element-plus';
import type { AxiosResponse, Result } from '../../hooks';
import type { ResolvedQuizItem } from './Editboard.vue';

export type QuizItem = {
  id: number;
  content: string;
  type: string;
  fraudType: string;
  tag: string[],
  answer: string;
  options: string[];
  checked: boolean;
};

type SelectionCellProps = {
  value: boolean;
  intermediate?: boolean;
  onChange: (value: CheckboxValueType) => void;
};

// const Checkbox = resolveDynamicComponent('ElCheckbox') as typeof ElCheckbox;

const TAG_TYPE_MAP = [
  undefined,
  'success',
  'info',
  'warning',
  'danger'
];

defineEmits(['router-push']);

const loading = ref(true);
const currentIndex = ref(0);
const showEditModal = ref(false);
const newItem = ref<QuizItem | null>();
const selectedItems = ref<number[]>([]);
const search = ref('');
const filteredData = ref<QuizItem[] | undefined>(undefined);
const QuizContainerRef = ref<HTMLDivElement>();
const containerHeight = ref<number>(-1);
const containerWidth = ref<number>(-1);

const dataIndexMap = new Map();

const containerMaxHeight = computed(() => containerHeight.value ? `${containerHeight.value}px` : 'none');

const debouncedFilterFn = useDebounceFn(() =>
  filteredData.value = data.value.filter(row =>
    row.content.toLowerCase().includes(search.value.toLowerCase())
  )
, 500);

watch(
  () => search.value,
  debouncedFilterFn
);

const SelectionCell: FunctionalComponent<SelectionCellProps> = ({
  value,
  intermediate = false,
  onChange
}) => {
  return <ElCheckbox
            onChange={onChange}
            modelValue={value}
            indeterminate={intermediate}
         />
};

const columns = ref<Array<Column>>([
  {
    key: 'selection',
    width: 30,
    cellRenderer: ({ rowData }) => {
      const onChange = (value: CheckboxValueType) => {
        rowData.checked = value;
        if(value) selectedItems.value.push(rowData.id);
        else selectedItems.value.splice(selectedItems.value.findIndex(id => id === rowData.id), 1);
      }
      return <SelectionCell value={rowData.checked} onChange={onChange} />
    },
    headerCellRenderer: () => {
      const _data = unref(data);
      const onChange = (value: CheckboxValueType) => 
        {
          data.value = _data.map(row => {
            row.checked = value as boolean;
            if(value)selectedItems.value.push(row.id);
            return row;
          });
          if(!value) selectedItems.value = [];
        };
      const allSelected = _data.every(row => row.checked);
      const containsChecked = _data.some(row => row.checked);

      return (
        <SelectionCell
          value={allSelected}
          intermediate={containsChecked && !allSelected}
          onChange={onChange}
        />
      );
    }
  },
  {
    title: '题型',
    width: 80,
    dataKey: 'type',
    key: 'type'
  },
  {
    title: '题目',
    width: 950,
    dataKey: 'content',
    key: 'content',
    headerCellRenderer: () => <div class="quiz-search">
                                <span class="quiz-header__title-content">题目</span>
                                <ElInput v-model={search.value} placeholder="请输入" size="small">
                                  {{ prefix: () => <ElIcon><Search /></ElIcon> }}
                                </ElInput>
                              </div>
  },
  {
    title: '答案',
    width: 50,
    dataKey: 'answer',
    key: 'answer'
  },
  {
    title: '诈骗类型',
    width: 150,
    dataKey: 'fraudType',
    key: 'fraudType'
  },
  {
    title: '关键词',
    width: 200,
    dataKey: 'tag',
    key: 'tag',
    cellRenderer: row => {
      const { tag } = row.rowData as QuizItem;
      return <div>
              {
                tag?.map(value => 
                  <ElTag type={TAG_TYPE_MAP[tag.indexOf(value)] as any} class="table-item-tag">
                    {value}
                  </ElTag>
                )
              }
             </div>;
    }
  },
  {
    title: '操作',
    width: 150,
    key: 'operaion',
    cellRenderer: row => {
      const { rowData: { id } } = row;
      const editItem = () => toEdit(id);
      const deleteItem = () => toDelete(id);
      return <>
              <ElButton size="small" onClick={editItem}>编辑</ElButton>
              <ElButton size="small" type="danger" onClick={deleteItem}>删除</ElButton>
             </>
    },
    headerCellRenderer: () => {
      return <>操作
              <ElButton type="primary" style="position: absolute; right: 0;" text bg onClick={toAdd}>+ 添加题目</ElButton>
             </>
    }
  },
]);

const data = ref<QuizItem[]>([]);

getAllQuizs().then((response: AxiosResponse) => {
  const res: Result = response.data
  if(res.code === 1) {
    const rows = res.data as unknown as any[];
    let index = 0;
    data.value = rows.map(val => {
      let _options = [
        val.aoption,
        val.boption,
        val.coption,
        val.doption,
      
      ];
      const options = _options.filter(option => option && option !== 'null');
      const tag = val.fraudTag && val.fraudTag !== 'null'
                  ? val.fraudTag.split('，')
                  : undefined;
      dataIndexMap.set(val.id, index++);
      return {
        id: val.id,
        content: val.fraudSubjectName,
        type: val.fraudOptionType,
        fraudType: val.fraudType,
        answer: val.answer,
        options,
        tag
      } as QuizItem;
    });
    setTimeout(() => loading.value = false, 1000);
  }
})

const toEdit = (id: number) => {
  newItem.value = null;
  const index = dataIndexMap.get(id);
  currentIndex.value = index;
  showEditModal.value = true;
};

const toDelete = (id: number) => {
  const index = dataIndexMap.get(id);
  deleteQuizItem(id)
    .then(response => {
      const res: Result = response.data;
      if(res.code === 1) {
        data.value.splice(index, 1);
        // TODO: 遍历MAP修改之后的index
        dataIndexMap.forEach((value, key) => 
          value > index 
          ? dataIndexMap.set(key, --value)
          : undefined
        );
        dataIndexMap.delete(id);
        if(search.value) debouncedFilterFn();
        ElMessage({ type: 'success', message: '删除成功' });
      } else ElMessage({ type: 'error', message: res.msg });
    });
};

const toAdd = () => {
  newItem.value = {
    id: -1,
    content: '',
    type: '单选题',
    fraudType: '',
    tag: [],
    answer: '',
    options: [],
    checked: false
  };
  showEditModal.value = true;
};

const onAddItem = (val: ResolvedQuizItem) => {
  delete val.checkboxAnswer;
  const item: QuizItem = val;
  dataIndexMap.set(item.id, data.value.length);
  data.value.push(item);
  if(search.value) debouncedFilterFn();
};

const deleteSeleted = () => {
  batchDeleteQuizItems({
    data: undefined,
    config: { 
      data: selectedItems.value
    }
  })
    .then(response => {
      const res: Result = response.data;
      if(res.code === 1) {
        selectedItems.value.forEach((id) => {
          const index = dataIndexMap.get(id);
          data.value.splice(index, 1);
          dataIndexMap.forEach((value, key) => 
            value > index 
            ? dataIndexMap.set(key, --value)
            : undefined
          );
          dataIndexMap.delete(id);
        });
        selectedItems.value = [];
        if(search.value) debouncedFilterFn();
        ElMessage({ type: 'success', message: '删除成功' });
      } else ElMessage({ type: 'error', message: res.msg });
    });
};

const pushSelected = () => {
  const _data = unref(data);
  data.value = _data.map(row => {
    row.checked = false;
    return row;
  });
  selectedItems.value = [];
  ElMessage({ type: 'success', message: '推送成功' });
}

onMounted(() => {
  const width = QuizContainerRef.value!.offsetWidth;
  const height = QuizContainerRef.value!.offsetHeight;
  containerWidth.value = width;
  containerHeight.value = height;
})
</script>
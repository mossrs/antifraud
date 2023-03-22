<template>
  <transition name="edit-overlay">
    <div class="edit-overlay" v-show="modelValue"></div>
  </transition>
  <transition name="edit-modal">
    <div class="edit-container" v-show="modelValue">
      <div class="edit-header">
        <span>{{ isAddItem ? '添加' : '编辑' }}</span>
        <el-icon :size="24" class="edit-close" @click="updateVisible(false)"><CloseBold /></el-icon>
      </div>
      <el-form
        :model="form" 
        :rules="isAddItem ? (isValidating ? rules : undefined) : rules"
        ref="FormRef"
        label-width="120px"
        class="edit-form"
        :hide-required-asterisk="!isAddItem"
        @submit.prevent
      >
        <el-form-item label="题目" prop="content">
          <el-input 
            type="textarea"
            v-model="form.content"
            :autosize="{ minRows: 2 }" 
            :input-style="{ fontSize: '16px' }"
            clearable
          />
        </el-form-item>
        <el-form-item label="题型" prop="type" inline-message>
          <el-select v-model="form.type">
            <el-option label="单选题" value="单选题" />
            <el-option label="多选题" value="多选题" />
          </el-select>
        </el-form-item>
        <el-form-item label="诈骗类型" prop="fraudType" inline-message>
          <el-select
            v-model="form.fraudType"
            filterable
            allow-create
            default-first-option
            placeholder="请选择或输入"
          >
            <el-option
              v-for="(item, index) in fraudTypes"
              :key="index"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="选项" prop="answer">
          <div class="edit-answer" v-show="!isEditOptions">
            <el-radio-group
              v-if="form.type === '单选题'"
              v-model="form.answer" 
              class="edit-radio-group"
            >
              <el-radio
                v-for="(option, index) in form.options"
                :label="OPTION_MAP[index]"
              >
                {{ `${OPTION_MAP[index]}.${option}` }}
              </el-radio>
            </el-radio-group>
            <el-checkbox-group v-else v-model="form.checkboxAnswer" class="edit-checkbox-group">
              <el-checkbox v-for="(option, index) in form.options" :label="OPTION_MAP[index]">
                {{ option }}
              </el-checkbox>
            </el-checkbox-group>
            <el-button text bg @click="isEditOptions = true">编辑选项</el-button>
          </div>
          <EditOptions v-show="isEditOptions" v-model="form.options" @complete="isEditOptions = false" />
        </el-form-item>
        <el-form-item label="关键词">
          <el-tag
            v-for="(tag, index) in form.tag"
            :key="tag"
            :type="TAG_TYPE_MAP[index % 4]"
            size="large"
            class="edit-tag"
            closable
            @close="handleTagClose(tag)"
          >
            {{ tag }}
          </el-tag>
          <el-select
            v-if="tagInputVisible"
            ref="TagInputRef"
            class="edit-tag-input"
            v-model="tagInputValue"
            filterable
            placeholder="请选择"
            @change="handleTagInputConfirm"
          >
            <el-option 
              v-for="item in fraudTags"
              :key="item"
              :value="item"
            />
          </el-select>
          <el-button v-else class="edit-new-tag" @click="showTagInput">
            + 新关键词
          </el-button>
        </el-form-item>
        <el-form-item class="edit-operations">
          <el-button type="primary" @click="onSubmit(FormRef)">{{ isAddItem ? '确定' : '保存' }}</el-button>
          <el-button @click="updateVisible(false)">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </transition>
</template>

<script lang="ts" setup>
import { CloseBold } from '@element-plus/icons-vue';
import { ElMessage, ElInput } from 'element-plus';
import { ref, reactive, computed, nextTick, watch } from 'vue';
import {
  updateQuizItem,
  getAllFraudTypes,
  addQuizItem,
  getAllTags
} from '../../hooks';
import EditOptions from './EditOptions.vue';

import type { FormRules, FormInstance } from 'element-plus';
import type { Result } from '../../hooks';
import type { QuizItem } from './Quiz.vue';

export interface ResolvedQuizItem extends QuizItem {
  checkboxAnswer?: string[]
};

type SubmitData = {
  id?: number;
  fraudType: string;
  fraudSubjectName: string;
  fraudOptionType: string;
  fraudTag: string;
  aoption: string | null;
  boption: string | null;
  coption: string | null;
  doption: string | null;
  answer: string;
}

const props = defineProps<{ modelValue: boolean, editItem: QuizItem }>();

const emit = defineEmits(['update:modelValue', 'add-item']);

const OPTION_MAP = ['A', 'B', 'C', 'D'];
const TAG_TYPE_MAP = [
  undefined,
  'success',
  'info',
  'warning',
  'danger'
];
const isValidating = ref(false);
const tagInputVisible = ref(false);
const tagInputValue = ref('');
const TagInputRef = ref<InstanceType<typeof ElInput>>();
const FormRef = ref<FormInstance>();
const editItemRef = ref<QuizItem>();
const isEditOptions = ref(false);
const fraudTypes = ref<string[]>([]);
const fraudTags = ref<string[]>([]);
const form = reactive<ResolvedQuizItem>({
  id: 0,
  content: '',
  type: '',
  fraudType: '',
  tag: [],
  answer: '',
  options: [],
  checked: props.editItem?.checked || false
}); // 用 reactive API 的话，好像只能这么初始化，有没有什么更优雅的方法？
const rules = reactive<FormRules>({
  content: {
    required: true,
    message: '题目不可为空'
  },
  type: {
    required: true,
    message: '题型不可为空'
  },
  fraudType: {
    required: true,
    message: '诈骗类型不可为空'
  },
  answer: {
    required: true,
    message: '选项及答案不可为空'
  }
});

const isAddItem = computed(() => props.editItem?.id === -1);

await getAllFraudTypes().then(response => {
  const res: Result = response.data;
  if(res.code === 1) fraudTypes.value = res.data as string[];
  else ElMessage({ type: 'error', message: '获取数据失败' });
});

await getAllTags().then(response => {
  const res: Result = response.data;
  if(res.code === 1) fraudTags.value = res.data as string[];
  else ElMessage({ type: 'error', message: '获取数据失败' });
});

watch(
  () => props.editItem,
  val => {
    editItemRef.value = val;
    const item: QuizItem = JSON.parse(JSON.stringify(val));
    Object.assign(form, item);
    form.options = form.options.map(
      val =>
        /^[A-D]/g.test(val)
        ? val.slice(val.indexOf('.') + 1)
        : val
    );
    if(item.type === '多选题') form.checkboxAnswer = item.answer.split('');
  }
);

const updateVisible = (val: boolean) => emit('update:modelValue', val);

const handleTagClose = (tag: string) => {
  const tags = form.tag;
  tags.splice(tags.indexOf(tag), 1);
};

const showTagInput = () => {
  tagInputVisible.value = true;
  nextTick(() => TagInputRef.value!.focus());
};

const handleTagInputConfirm = () => {
  if(tagInputValue.value) form.tag.push(tagInputValue.value);
  tagInputVisible.value = false;
  tagInputValue.value = '';
};

const onSubmit = async (formEl: FormInstance | undefined) => {
  if(!formEl) return;
  isValidating.value = true;
  await formEl.validate(valid => {
    if(!valid) return;
    if(isAddItem.value) addItem();
    else saveEdit();
  })
};

const parseSubmitData = () => {
  const originalData: ResolvedQuizItem = JSON.parse(JSON.stringify(form));
  const { type, tag, options, checkboxAnswer, answer } = originalData;
  const submitData: SubmitData = {
    fraudType: originalData.fraudType,
    fraudSubjectName: originalData.content,
    fraudOptionType: type,
    fraudTag: tag.join('，'),
    aoption: options[0] || null,
    boption: options[1] || null,
    coption: options[2] || null,
    doption: options[3] || null,
    answer: checkboxAnswer ? checkboxAnswer.join('') : answer
  };
  if(!isAddItem.value) submitData.id = form.id;
  return { originalData, submitData };
}

const saveEdit = () => {
  const { originalData, submitData } = parseSubmitData();
  updateQuizItem({ data: submitData }).then(response => {
    const res: Result = response.data;
    if(res.code === 1) {
      Object.assign(editItemRef.value as object, originalData);
      ElMessage({ type: 'success', message: '保存成功' });
      updateVisible(false);
    } else ElMessage({ type: 'error', message: res.msg });
  });
};

const addItem = () => {
  const { originalData, submitData } = parseSubmitData();
  addQuizItem({ data: submitData }).then(response => {
    const res: Result = response.data;
    if(res.code === 1) {
      const id = res.data as number;
      originalData.id = id;
      emit('add-item', originalData);
      ElMessage({ type: 'success', message: '添加成功' });
      updateVisible(false);
    } else ElMessage({ type: 'error', message: res.msg });
  });
}
</script>

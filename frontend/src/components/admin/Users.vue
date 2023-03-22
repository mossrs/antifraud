<template>
  <div class="classes-container" ref="ClassesContainerRef">
    <transition name="class-table-fade" @enter="setPagination">
      <div v-show="activeIndex !== undefined" class="class-table-container">
        <div class="class-table" ref="ClassTableRef">
          <el-table :data="currentClassData" style="width: 100%;" height="100%" stripe>
            <el-table-column type="index" />
            <el-table-column prop="studentCard" label="学号" />
            <el-table-column prop="studentName" label="姓名" />
            <el-table-column prop="sex" label="性别" />
            <el-table-column prop="age" label="年龄" />
            <el-table-column prop="phone" label="手机" />
            <el-table-column prop="email" label="邮箱" />
            <el-table-column prop="major" label="专业" />
            <el-table-column label="用户画像" :width="200">
              <template #default="scope">
                <el-button type="primary" :icon="View" text @click="toPersonaModal(scope.$index)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <el-pagination
          layout="prev, pager, next" 
          :total="classData?.length"
          :page-size="pageSize"
          v-model:current-page="currentPage"
          hide-on-single-page
        />
      </div>
    </transition>
    <div class="classes-row" v-for="n in totalRowNum" v-if="isLoaded">
      <div
        v-for="(item, index) in classes?.slice((n - 1) * eachRowNum, n * eachRowNum)" 
        :key="index"
        :class="[
          'class-card', 
          activeIndex === undefined
          ? ''
          : activeIndex === index + (n - 1) * eachRowNum
            ? 'active'
            : 'hidden'
          ]"
        :style="[
          index !== eachRowNum - 1
          ? { marginRight: `${cardMarginRight}px` }
          : {},
          activeIndex === undefined
          ? {}
          : activeIndex === index + (n - 1) * eachRowNum
            ? { height: `${cardFullHeight}px`, width: `${cardFullWidth}px`, marginRight: 0 }
            : {}
        ]"
        @click="toClass(index + (n - 1) * eachRowNum)"
      >
        <div class="card__backbtn"
          @click.stop="
            activeIndex = undefined,
            atIndex = undefined,
            cardFullHeight = undefined,
            cardFullWidth = undefined
            "
        >
          <svg t="1660381703977" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10704" width="20" height="20"><path d="M761.248866 208.843017 461.531611 512.526663l299.717255 303.68851c9.265857 9.259775 16.212208 19.845329 20.84392 31.756662 4.629279 11.906468 6.946352 23.984435 6.946352 36.224172 0 12.23852-2.317072 24.148637-6.946352 35.729133-4.631712 11.573198-11.578064 21.998199-20.84392 31.256758-18.524415 19.189737-40.689249 28.782782-66.492069 28.782782-25.804036 0-47.96887-9.593044-66.493285-28.782782L266.024226 585.971165c-9.259775-9.26464-16.539396-20.344016-21.831565-33.249075-5.293385-12.898977-8.272131-25.969454-8.932587-39.201701-0.660457-13.23103 0.993726-25.965806 4.960116-38.209191 3.972471-12.239737 10.585554-22.326603 19.851411-30.265465l368.190695-371.16944c18.523199-18.525632 40.689249-27.790272 66.493285-27.790272 25.80282 0 47.967654 9.26464 66.492069 27.790272 9.265857 9.259775 16.212208 19.845329 20.84392 31.756662 4.629279 11.906468 6.946352 23.984435 6.946352 36.222955 0 12.239737-2.317072 24.15107-6.946352 35.729133C777.461074 189.159458 770.514723 199.583242 761.248866 208.843017L761.248866 208.843017zM761.248866 208.843017" p-id="10705"></path></svg>
        </div>
        <div class="card__header">
          <span class="card__header__bar"></span>
          <span class="card__header__title">{{ item.name }}</span>
        </div>
        <div class="card__number" v-show="activeIndex === undefined">{{ item.num }}人</div>
      </div>
    </div>
    <teleport to='body'>
      <PersonaModal v-model="PersonaModalVisible" />
    </teleport>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted, provide } from 'vue';
import { getAllClasses, getAllUsers } from '../../hooks';
import { ElMessage } from 'element-plus';
import { View } from '@element-plus/icons-vue';
import PersonaModal from './PersonaModal.vue';

import type { Result } from '../../hooks';

interface Clazz {
  id: number;
  name: string;
  num: string;
  fraudSubjectNum: number;
  fraudNum: number;
  antiFraudLevel: number;
  createTime?: null;
  updateTime?: null;
};
interface User {
  studentCard: number;
  studentName: string;
  sex: string;
  age: number;
  phone: string;
  email: string;
  major: string;
};

const isLoaded = ref<boolean>(false);
const classes = ref<Clazz[]>();
const activeIndex = ref<number>();
const atIndex = ref<number>();
const classData = ref<User[]>();
const currentPage = ref<number>();
const pageSize = ref<number>();
const cardFullHeight = ref<number>();
const cardFullWidth = ref<number>();
const ClassesContainerRef = ref<HTMLDivElement>();
const ClassTableRef = ref<HTMLDivElement>();
const PersonaModalVisible = ref<boolean>(false);
const currentStuIndex = ref<number>(-1);

const currentClassData = computed(
  () => classData.value?.slice(
    (currentPage.value! - 1) * pageSize.value!,
    currentPage.value! * pageSize.value!
  )
);

const containerWidth = ref<number>(0);

const cardMarginRight = computed(
  () => (containerWidth.value
        - eachRowNum.value * 298
        ) / (eachRowNum.value - 1)
);

const eachRowNum = ref<number>(1);

const totalRowNum = computed(
  () => Math.ceil((classes.value!.length + 1) / eachRowNum.value)
);

const basicInfo = computed(() => {
  return currentStuIndex.value === -1
          ? []
          : [
              {
                name: '学号',
                value: classData.value![currentStuIndex.value].studentCard
              },
              {
                name: '姓名',
                value: classData.value![currentStuIndex.value].studentName
              },
              {
                name: '性别',
                value: classData.value![currentStuIndex.value].sex
              },
              {
                name: '年龄',
                value: classData.value![currentStuIndex.value].age
              }
            ];
})

provide('basicInfo', basicInfo);

getAllClasses().then(response => {
  const res: Result = response.data;
  if(res.code === 1) {
    const width = ClassesContainerRef.value!.offsetWidth;
    classes.value = res.data as Clazz[]; 
    eachRowNum.value = Math.floor(width / 308);
    containerWidth.value = width;
    isLoaded.value = true;
  }
  else ElMessage({ type: 'error', message: res.msg });
});

const toClass = (index: number) => {
  getAllUsers({
    data: {
      className: classes.value![index].name
    }
  }).then(response => {
    const res: Result = response.data;
    if(res.code === 1) {
      classData.value = res.data as User[];
      currentStuIndex.value = 0;
      activeIndex.value = index;
      cardFullHeight.value = ClassesContainerRef.value?.offsetHeight! - 40;
      cardFullWidth.value = ClassesContainerRef.value?.offsetWidth;
    }
    else ElMessage({ type: 'error', message: res.msg });
  });
};

const setPagination = () => {
  const height = ClassTableRef.value?.offsetHeight;
  pageSize.value = Math.floor(height! / 30);
  currentPage.value = 1;
};

const toPersonaModal = (index: number) => {
  currentStuIndex.value = index;
  PersonaModalVisible.value = true;
};



onMounted(() => {
  window.onresize = () => {
    eachRowNum.value = Math.floor(ClassesContainerRef.value!.offsetWidth / 308);
    setPagination();
    containerWidth.value = ClassesContainerRef.value!.offsetWidth;
  };
});

onUnmounted(() => {
  window.onresize = null;
});

</script>
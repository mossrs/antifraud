<template>
  <div class="persona-wrapper">
    <div class="return">
        <img class="returnBtn" src="../../img/导航栏/返回按钮.png" alt="" @click="router.back()">
        <div class="returnTxt">
            返回
        </div>
    </div>
    <div class="persona-container">
      <suspense>
        <Persona />
      </suspense>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, provide, defineAsyncComponent } from 'vue';
import { useRouter } from 'vue-router';
import { getStudentInfo } from '../../hooks';

import type { Result } from '../../hooks';

interface Info {
  '学号'?: string;
  '姓名'?: string;
  '性别'?: string;
  '年龄'?: string;
};

const router = useRouter();

const info = ref<Info>({ '学号': 'guest' });
const basicInfo = computed(() => Object.entries(info.value).map(
  ([key, value]) => ({ name: key, value })
));

provide('basicInfo', basicInfo);
const Persona = defineAsyncComponent(
  () => import('../Persona.vue')
);

getStudentInfo().then(response => {
  const res: Result = response.data;
  if(res.code === 1) {
    const { data } = res;
    info.value = {
      '学号': data['studentCard'],
      '姓名': data['studentName'],
      '性别': data['sex'],
      '年龄': data['age']
    };
  }
})
</script>

<style lang="scss" scoped>
  .persona-wrapper {
    width: 100%;
    height: 100%;
    background-color: #141414;
    display: flex;
    align-items: center;
    justify-content: center;
    .persona-container {
    padding: 0;
    width: 80%;
    height: 700px;
  }
  }
  

  .return {
    position: absolute;
    top: 2%;
    // left: 20%;
    left: 1.3%;
    cursor: pointer;
    z-index: 100;


    .returnBtn {

        cursor: pointer;
    }

    .returnTxt {
        // position: absolute;

        margin-top: -7.5%;
        margin-left: 78%;
        width: 58px;
        height: 30px;
        line-height: 30px;
        border: .4px solid;
        border-radius: 3px 15px 15px 15px;
        visibility: hidden;
        background-color: rgba(255, 255, 255, 0.719);
    }
}


.return:hover .returnTxt {
    visibility: visible;
}
</style>
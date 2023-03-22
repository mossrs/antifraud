<template>
  <div class="setting-container">
    <el-form label-position="right" :label-width="76" @submit.prevent>
      <el-form-item label="诈骗标签">
        <el-tag 
        v-for="(item, index) in showedTags" 
        :key="index"
        :type="TAG_TYPE_MAP[index % 5]"
        size="large"
        class="setting-tag-item"
        closable
      >
        {{item}}
      </el-tag>
      </el-form-item>
      <el-form-item>
        <el-input
          v-if="inputVisible"
          ref="InputRef"
          v-model="inputValue"
          @keyup.enter="handleInputConfirm"
          @blur="handleInputConfirm"
          size="default"
          style="width: 108px;"
        />
        <el-button :icon="Plus" @click="showInput" v-else>添加标签</el-button>
      </el-form-item>
      <el-form-item label="实时监测">
        <el-switch v-model="isRealTimeMonitoring" />
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref, nextTick, computed } from 'vue';
import { getAllTags, addFraudTag } from '../../hooks';

import { ElInput, ElMessage } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';

import type { Result } from '../../hooks';

const TAG_TYPE_MAP = [
  undefined,
  'success',
  'info',
  'warning',
  'danger'
];

const tags = ref<string[]>([]);
const addedTags = ref<string[]>([]);
const inputVisible = ref<boolean>(false);
const inputValue = ref<string>();
const InputRef = ref<InstanceType<typeof ElInput>>();
const isRealTimeMonitoring = ref<boolean>(true);

const showedTags = computed(() => [...tags.value.slice(0, 5), ...addedTags.value])

getAllTags().then(response => {
  const res: Result = response.data;
  if(res.code === 1) {
    tags.value = res.data as string[];
  }
});

const handleInputConfirm = () => {
  if (inputValue.value) {
    addFraudTag({ data: { tagName: inputValue.value } })
      .then(response => {
        const res: Result = response.data;
        if(res.code === 1) {
          addedTags.value!.push(inputValue.value!);
          inputValue.value = '';
          ElMessage({ type: 'success', message: '添加成功' });
        }
      });
  }
  inputVisible.value = false;
};

const showInput = () => {
  inputVisible.value = true;
  nextTick(() => {
    InputRef.value!.input!.focus();
  });
}
</script>
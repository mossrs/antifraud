<template>
  <div class="edit-options">
    <transition-group name="edit-options">
      <el-input
        v-for="(item, index) in _options"
        v-model="_options[index]"
        :key="index"
        :input-style="{ fontSize: '15px' }"
        @change="updateOptions"
        @keydown.enter="item && _options.push('')"
        clearable
      >
        <template #prepend>{{ OPTION_MAP[index] }}</template>
      </el-input>
    </transition-group>
    <el-button-group>
      <el-button type="success"
        text bg 
        v-show="_options.length < 4"
        @click="_options.push('')"
      >
        增加选项
      </el-button>
      <el-button 
        type="danger" 
        text bg 
        v-show="_options.length"
        @click="_options.splice(-1, 1)"
      >
        删除选项
      </el-button>
      <el-button type="primary" text bg @click="back">返回</el-button>
    </el-button-group>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import { ElInput } from 'element-plus';

const props = defineProps<{ modelValue: string[] }>();
const emit = defineEmits(['update:modelValue', 'complete']);

const OPTION_MAP = ['A', 'B', 'C', 'D'];

const _options = ref<string[]>([]);

const updateOptions = () => {
  emit('update:modelValue', _options.value);
};

const back = () => {
  if(_options.value.length === 1 && !_options.value[0])
    _options.value = [];
  updateOptions();
  emit('complete');
}

watch(
  () => props.modelValue,
  val => {
    _options.value = [...val];
    if(!_options.value.length) _options.value.push('');
  }
);

</script>
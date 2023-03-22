<template>
  <transition name="edit-overlay">
    <div class="edit-overlay" v-if="modelValue"></div>
  </transition>
  <transition name="edit-modal">
    <div class="edit-container dark" v-if="modelValue">
      <div class="edit-header">
        <span>用户画像</span>
        <el-icon :size="24" class="edit-close" @click="updateVisible(false)"><CloseBold /></el-icon>
      </div>
        <suspense>
          <Persona />
        </suspense>
    </div>
  </transition>
</template>

<script setup lang="ts">
import { defineAsyncComponent } from 'vue';
import { CloseBold } from '@element-plus/icons-vue';

defineProps<{ modelValue: boolean }>();

const emit = defineEmits(['update:modelValue']);

const updateVisible = (val: boolean) => emit('update:modelValue', val);

const Persona = defineAsyncComponent(
  () => import('../Persona.vue')
);

</script>
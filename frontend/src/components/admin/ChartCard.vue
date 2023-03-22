<template>
  <div
    class="chart-card"
    ref="Canvas"
  ></div>
</template>

<script lang="ts" setup>
import { onMounted, ref, watch } from 'vue';
import * as echarts from 'echarts/core';
import 'echarts-wordcloud';
import { BarChart, PieChart, RadarChart, LineChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent
} from 'echarts/components';
import { LabelLayout, UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';

import DarkTheme from '../../assets/dark.project.json';

import type { ECharts, EChartsCoreOption } from 'echarts/core';

const props = defineProps<{
  option: EChartsCoreOption;
  useDarkTheme?: boolean;
}>();

echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent,
  LegendComponent,
  BarChart,
  PieChart,
  LineChart,
  RadarChart,
  LabelLayout,
  UniversalTransition,
  CanvasRenderer
]);
echarts.registerTheme('dark', DarkTheme);

const Canvas = ref<HTMLDivElement>();
const myChart = ref<ECharts>();

onMounted(() => {
  myChart.value = echarts.init(
    Canvas.value as unknown as HTMLDivElement,
    props.useDarkTheme ? 'dark' : undefined
  );
  myChart.value.setOption(props.option);
});

watch(
  props.option,
  val => {
    myChart.value?.setOption(val)
  }
);

</script>
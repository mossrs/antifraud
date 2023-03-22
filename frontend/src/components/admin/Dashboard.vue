<template>
  <div class="header-numbers">
    <div class="number-card" v-for="(item, index) in numbers" :key="index">
      <div class="number-card__main">
        <div>
          <p class="number">{{ item.num }}</p>
          <span class="number-desc">{{ item.desc }}</span>
        </div>
        <img class="number-icon" :src="item.imgUrl">
      </div>
    </div>
  </div>
  <div class="charts-container">
    <ChartCard :option="radarOption" />
    <ChartCard :option="lineOption" />
    <ChartCard :option="barOption" />
    <ChartCard :option="pieOption" />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import * as echarts from 'echarts/core';
import {
  getTotalNumber,
  getDefraudedNumber,
  getPushedNumber,
  getCompletedNumber,
  getEachFraudTypesWarning,
  getQuizTrend,
  getEachFraudTypesDefraudedNumbers,
  getEachFraudTypesDefraudedRatio
} from '../../hooks';
import { ElMessage } from 'element-plus';

import ChartCard from './ChartCard.vue';

import type { Result, AxiosResponse } from '../../hooks';

const FRAUD_TYPE_MAP = [
  '电信诈骗',
  '信用卡诈骗',
  '网络诈骗',
  '新型诈骗',
  '冒充熟人诈骗'
];
const WARNING_MAP = [
  '优秀',
  '良好',
  '一般',
  '警告',
  '危险'
];
const TOOLTIP_COLOR_MAP = {
  '优秀': '#2ecc71',
  '良好': '#0097e6',
  '一般': '#7f8c8d',
  '警告': '#f39c12',
  '危险': '#e74c3c'
}

const numbers = ref<{ num: number, desc: string, imgUrl: string }[]>([
  {
    num: 0,
    desc: '总人数',
    imgUrl: new URL('../../img/admin/total_number.png', import.meta.url).href
  },
  {
    num: 0,
    desc: '被诈骗人数',
    imgUrl: new URL('../../img/admin/defrauded_number.png', import.meta.url).href
  },
  {
    num: 0,
    desc: '推送题目',
    imgUrl: new URL('../../img/admin/pushed_number.png', import.meta.url).href
  },
  {
    num: 0,
    desc: '人均完成题目',
    imgUrl: new URL('../../img/admin/completed_number.png', import.meta.url).href
  }
]);
const outterSum = ref<number>(0);
const innerSum = ref<number>(0);

Promise.all([
  getTotalNumber(),
  getDefraudedNumber(),
  getPushedNumber(),
  getCompletedNumber()
]).then(
  responses => {
    responses.forEach((response, index) => {
      const res: Result = response.data;
      if(res.code === 1) numbers.value[index].num = res.data as number;
      else ElMessage({ type: 'error', message: res.msg });
    })
  }
);

const radarOption = reactive({
  title: {
    text: '诈骗类型预警'
  },
  tooltip: {
    trigger: 'item',
    padding: 0,
    backgroundColor: 'transparent',
    formatter: (params) => {
      const data: string[] = params.value;
      const warnings = data.map(val => WARNING_MAP[parseInt(val) - 1]);
      return `
        <div class="radar-tooltip">
          <p class="radar-tooltip__title">诈骗类型</p>
          <ul class="radar-tooltip__list">
            <li class="radar-tooltip__item">
              <div>
                <span></span>
                <span>${FRAUD_TYPE_MAP[0]}</span>
              </div>
              <span style="color: ${TOOLTIP_COLOR_MAP[warnings[0]]}">${warnings[0]}</span>
            </li>
            <li class="radar-tooltip__item">
              <div>
                <span></span>
                <span>${FRAUD_TYPE_MAP[1]}</span>
              </div>
              <span style="color: ${TOOLTIP_COLOR_MAP[warnings[1]]}">${warnings[1]}</span>
            </li>
            <li class="radar-tooltip__item">
              <div>
                <span></span>
                <span>${FRAUD_TYPE_MAP[2]}</span>
              </div>
              <span style="color: ${TOOLTIP_COLOR_MAP[warnings[2]]}">${warnings[2]}</span>
            </li>
            <li class="radar-tooltip__item">
              <div>
                <span></span>
                <span>${FRAUD_TYPE_MAP[3]}</span>
              </div>
              <span style="color: ${TOOLTIP_COLOR_MAP[warnings[3]]}">${warnings[3]}</span>
            </li>
            <li class="radar-tooltip__item">
              <div>
                <span></span>
                <span>${FRAUD_TYPE_MAP[4]}</span>
              </div>
              <span style="color: ${TOOLTIP_COLOR_MAP[warnings[4]]}">${warnings[4]}</span>
            </li>
          </ul>
        </div>
      `
    }
  },
  radar: {
    indicator: [
      { name: FRAUD_TYPE_MAP[0], max: 5 },
      { name: FRAUD_TYPE_MAP[1], max: 5 },
      { name: FRAUD_TYPE_MAP[2], max: 5 },
      { name: FRAUD_TYPE_MAP[3], max: 5 },
      { name: FRAUD_TYPE_MAP[4], max: 5 },
    ],
    radius: 120,
    axisName: {
      fontSize: 14,
      color: '#666'
    },
    splitNumber: 5
  },
  series: [
    {
      type: 'radar',
      name: '诈骗类型',
      lineStyle: {
        color: '#0097e6'
      },
      itemStyle: {
        color: '#0097e6'
      },
      emphasis: {
        lineStyle: {
          width: 4
        }
      },
      data: [
        {
          value: [1, 2, 3, 4, 5],
          areaStyle: {
            color: new echarts.graphic.RadialGradient(0.1, 0.6, 1, [
              {
                color: 'rgba(0, 151, 230, 0.1)',
                offset: 0
              },
              {
                color: 'rgba(0, 151, 230, 0.7)',
                offset: 1
              }
            ])
          }
        }
      ]
    }
  ]
});

const lineOption = reactive({
  title: {
    text: '推送与答题走势'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    top: '5%',
    left: 'center'
  },
  xAxis: {
    type: 'category',
    data: ['6天前', '5天前', '4天前', '3天前', '2天前', '1天前', '今天']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [0, 0, 0, 0, 0, 0, 0],
      type: 'line',
      name: '推送题目数',
      smooth: true,
      label: {
        show: true
      },
      areaStyle: {
        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
          {
            color: 'rgba(0, 151, 230, 0)',
            offset: 0
          },
          {
            color: 'rgba(0, 151, 230, 0.7)',
            offset: 1
          }
        ])
      }
    },
    {
      data: [0, 0, 0, 0, 0, 0, 0],
      type: 'line',
      name: '完成题目数',
      smooth: true,
      label: {
        show: true
      },
      areaStyle: {
        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
          {
            color: 'rgba(46, 204, 113, 0.1)',
            offset: 0
          },
          {
            color: 'rgba(46, 204, 113, 0.7)',
            offset: 1
          }
        ])
      }
    }
  ]
});

const barOption = reactive({
  title: {
    text: '诈骗类型受骗人数'
  },
  legend: {
    top: '5%',
    left: 'center',
    data: ['男', '女']
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  xAxis: {
    type: 'category',
    data: FRAUD_TYPE_MAP,
    boundaryGap: [0, 0.01]
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '男',
      data: [0, 0, 0, 0, 0],
      type: 'bar',
      label: {
        show: true
      },
      emphasis: {
        focus: 'series'
      },
    },
    {
      name: '女',
      data: [0, 0, 0, 0, 0],
      type: 'bar',
      label: {
        show: true
      },
      emphasis: {
        focus: 'series'
      },
    }
  ]
});

const pieOption = reactive({
  title: {
    text: '诈骗类型人群占比'
  },
  tooltip: {
    trigger: 'item',
    valueFormatter: (val: number) => `${(val * 100).toFixed(1)}%`
  },
  legend: {
    top: '10%',
    left: '0',
    orient: 'vertical'
  },
  series: [
    {
      name: '受骗',
      type: 'pie',
      selectedMode: 'single',
      radius: [0, '45%'],
      center: ['59%', '50%'],
      label: {
        show: false
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      data: [
        { value: 0, name: ''},
        { value: 0, name: ''},
        { value: 0, name: ''},
        { value: 0, name: ''},
        { value: 0, name: ''}
      ]
    },
    {
      name: '未受骗',
      type: 'pie',
      radius: ['55%', '70%'],
      center: ['59%', '50%'],
      label: {
        show: false
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      data: [
        { value: 0, name: ''},
        { value: 0, name: ''},
        { value: 0, name: ''},
        { value: 0, name: ''},
        { value: 0, name: ''}
      ]
    }
  ]
});

const responseCallbacks: ((data: Result['data']) => void)[] = [
  data => {
    Object.entries(data).map(([key, value]) => {
      const index = FRAUD_TYPE_MAP.indexOf(key);
      radarOption.series[0].data[0].value[index] = value;
    });
  },
  data => {
    lineOption.series[0].data = data[0] as number[];
    lineOption.series[1].data = data[1] as number[];
  },
  data => {
    Object.entries(data).map(([key, value]) => {
      const index = FRAUD_TYPE_MAP.indexOf(key);
      barOption.series[0].data[index] = value['男'];
      barOption.series[1].data[index] = value['女'];
    })
  },
  data => {
    const outterData = [
      { value: 0, name: ''},
      { value: 0, name: ''},
      { value: 0, name: ''},
      { value: 0, name: ''},
      { value: 0, name: ''}
    ];
    const innerData = [
      { value: 0, name: ''},
      { value: 0, name: ''},
      { value: 0, name: ''},
      { value: 0, name: ''},
      { value: 0, name: ''}
    ];
    Object.entries(data).map(([key, value]) => {
      const index = FRAUD_TYPE_MAP.indexOf(key);
      innerData[index].value = value[0];
      outterData[index].value = value[1];
      outterData[index].name = innerData[index].name = key;

      innerSum.value += parseInt(value[0]);
      outterSum.value += parseInt(value[1]);
    });
    innerData.forEach(ele => ele.value /= innerSum.value);
    outterData.forEach(ele => ele.value /= outterSum.value);

    pieOption.series[0].data = innerData;
    pieOption.series[1].data = outterData;
  }
];

const resolveResponse = (response: AxiosResponse, index: number) => {
  const res: Result = response.data;
  if(res.code === 1) responseCallbacks[index](res.data);
  else ElMessage({ type: 'error', message: res.msg });
};

Promise.all([
  getEachFraudTypesWarning(),
  getQuizTrend(),
  getEachFraudTypesDefraudedNumbers(),
  getEachFraudTypesDefraudedRatio()
]).then(
  responses => {
    responses.forEach((response, index) => resolveResponse(response, index));
  }
);
</script>
<template>
  <div class="persona-container">
    <div>
      <div class="basic-info-card">
        <div class="basic-info-card-title">基本信息</div>
        <ul class="basic-info-list">
          <li 
            class="basic-info-item" 
            v-for="(item, index) in basicInfo"
            :key="index"
          >
          {{item.name}}：{{item.value}}
          </li>
          <li class="basic-info-item" >性格特征：<span style="color: #66b1ff">{{personality}}</span></li>
          <li class="basic-info-item" >
            受骗程度：
            <span :class="`is-fraud-${isFraud}`">
              {{ isFraud ? '容易受骗' : '不易受骗' }}
            </span>
          </li>
          <li class="basic-info-item fraud-types" v-if="isFraud">
            <span>受骗类型：</span>
            <div>
              <el-tag 
                v-for="(item, index) in fraudTypes?.split(',')" 
                :key="index"
                :type="TAG_TYPE_MAP[index % 5]"
                effect="dark"
              >
                {{ item }}
              </el-tag>
            </div>
          </li>
        </ul>
      </div>
      <div class="persona-radar">
        <ChartCard :option="radarOption" :useDarkTheme="true" />
      </div>
    </div>
    <div class="persona-wordcloud">
      <span>标签</span>
      <div class="man-bg"></div>
      <ChartCard :option="wordcloudOption" :useDarkTheme="true" />
    </div>
    <div class="persona-bar">
      <ChartCard :option="barOption" :useDarkTheme="true" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, inject, reactive } from 'vue';
import { getPersonaByStuNumber } from '../hooks';
import * as echarts from 'echarts/core';

import ChartCard from './admin/ChartCard.vue';

import type { ComputedRef } from 'vue';
import type { Result } from '../hooks';

type BasicInfo = [{ name: string; value: string }];

interface PersonaData {
  studentCard: string;
  personality: string;
  fraudTags: string;
  fraudTypes: string;
  isFraud: boolean;
  fraudLevel: number[];
  answeredNum: number[];
  questionNum: number[];
};

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
  '一般': '#eeeeee',
  '警告': '#f39c12',
  '危险': '#e74c3c'
};
const TAG_TYPE_MAP = [
  undefined,
  'success',
  'info',
  'warning',
  'danger'
];

const maskImage = new Image();

const personality = ref<string>();
const isFraud = ref<boolean>();
const fraudTypes = ref<string>();

const basicInfo = inject('basicInfo') as ComputedRef<BasicInfo>;

const radarOption = reactive({
  title: {
    text: '诈骗类型预警',
    textAlign: 'center',
    bottom: 10,
    left: '50%'
  },
  tooltip: {
    trigger: 'item',
    padding: 0,
    backgroundColor: 'transparent',
    borderColor: '#58585B',
    formatter: (params) => {
      const data: string[] = params.value;
      const warnings = data.map(val => WARNING_MAP[parseInt(val) - 1]);
      return `
        <div class="radar-tooltip dark">
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
      color: '#CFD3DC'
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
const barOption = reactive({
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  legend: {},
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: {
    type: 'value',
    boundaryGap: [0, 0.01]
  },
  yAxis: {
    type: 'category',
    data: ['冒充熟人诈骗', '信用卡诈骗', '网络诈骗', '新型诈骗', '电信诈骗']
  },
  series: [
    {
      name: '累计答题数',
      type: 'bar',
      data: [0, 0, 0, 0, 0],
      label: {
        show: true
      }
    },
    {
      name: '推送题目数',
      type: 'bar',
      data: [0, 0, 0, 0, 0],
      label: {
        show: true
      }
    }
  ]
});
const wordcloudOption = reactive({
  series: [{
        type: 'wordCloud',
        backgroundColor: 'transparent',

        // The shape of the "cloud" to draw. Can be any polar equation represented as a
        // callback function, or a keyword present. Available presents are circle (default),
        // cardioid (apple or heart shape curve, the most known polar equation), diamond (
        // alias of square), triangle-forward, triangle, (alias of triangle-upright, pentagon, and star.

        // shape: 'circle',

        // Keep aspect ratio of maskImage or 1:1 for shapes
        // This option is supported from echarts-wordcloud@2.1.0
        keepAspect: true,

        // A silhouette image which the white area will be excluded from drawing texts.
        // The shape option will continue to apply as the shape of the cloud to grow.
        // maskImage,

        // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
        // Default to be put in the center and has 75% x 80% size.
        width: "100%",
        height: "100%",
        right: null,
        bottom: null,

        // Text size range which the value in data will be mapped to.
        // Default to have minimum 12px and maximum 60px size.

        sizeRange: [8, 24],

        // Text rotation range and step in degree. Text will be rotated randomly in range [-90, 90] by rotationStep 45

        rotationRange: [-10, 10],
        rotationStep: 5,

        // size of the grid in pixels for marking the availability of the canvas
        // the larger the grid size, the bigger the gap between words.

        gridSize: 0,

        // set to true to allow word being draw partly outside of the canvas.
        // Allow word bigger than the size of the canvas to be drawn
        drawOutOfBound: true,

        // If perform layout animation.
        // NOTE disable it will lead to UI blocking when there is lots of words.
        layoutAnimation: true,

        // Global text style
        textStyle: {
            fontFamily: "'Helvetica Neue',Helvetica,'PingFang SC','Hiragino Sans GB','Microsoft YaHei','微软雅黑',Arial,sans-serif",
            fontWeight: 'bold',
            // Color can be a callback function or a color string
            color: function () {
                // Random color
                return 'rgb(' + [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160)
                ].join(',') + ')';
            }
        },
        emphasis: {
            focus: 'self',

            textStyle: {
                textShadowBlur: 20,
                textShadowColor: '#bbb'
            }
        },

        // Data is an array. Each array item must have name and value property.
        data: [
          {
            name: 'Tag1',
            value: 231
          }
        ]
    }]
});

maskImage.src = new URL('../img/man_silhouette.png', import.meta.url).href;
maskImage.onload = () => {
  wordcloudOption.series[0]['maskImage'] = maskImage;
}

await getPersonaByStuNumber({
  data: { stuCard: basicInfo.value[0].value }
}).then(response => {
  const res: Result = response.data;
  if(res.code === 1) {
    const data = res.data as PersonaData;
    const _fraudLevel = data.fraudLevel.map(val => val + 1);
    let temp = _fraudLevel[4];
    _fraudLevel[4] = _fraudLevel[0], _fraudLevel[0] = temp;
    wordcloudOption.series[0].data = data.fraudTags.split('，').map(tag => {
      return {
        name: tag,
        value: Math.random() * 100
      }
    }).sort((a, b) => b.value - a.value);
    personality.value = data.personality;
    isFraud.value = data.isFraud;
    fraudTypes.value = data.fraudTypes;
    radarOption.series[0].data[0].value = _fraudLevel;
    barOption.series[0].data = data.answeredNum;
    barOption.series[1].data = data.questionNum;
  }
});

</script>

<style lang="scss">
  .persona-container {
    padding: 40px 20px 50px 0;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    .basic-info-card {
      position: relative;
      left: 50px;
      width: 270px;
      padding: 15px 30px 20px 30px;
      border-radius: 14px;
      border: 1px solid #636466;
      background-color: #1D1E1F;

      &-title {
        color: #E5EAF3;
        font-size: 24px;
        font-weight: 700;
        text-align: center;
        margin-bottom: 5px;
      }

      & .basic-info-list {
        margin: 0;
        list-style: none;
        padding: 0;

        & .basic-info-item {
          text-align: left;
          margin-top: 3px;
          font-size: 14px;
          color: #E5EAF3;

          & span {
            font-weight: 800;
          }

          & .is-fraud-true {
            color: #e74c3c;
          }

          & .is-fraud-false {
            color: #2ecc71;
          }

          &.fraud-types {
            display: inline-flex;
            flex-direction: row;
            justify-content: space-between;

            > span {
              white-space: nowrap;
            }

            > div {
              display: inline-flex;
              flex-wrap: wrap;
              justify-content: flex-start;

              .el-tag {
                margin-right: 5px;
                margin-bottom: 5px;
              }
            }
          }
        }
      }
    }

    .persona-radar {
      margin-top: 50px;
      width: 450px;
      height: 350px;

      & .chart-card {
        width: 100%;
        height: 100%;
      }
    }

    .persona-wordcloud {
      background-repeat: no-repeat;
      border-radius: 16px;
      width: 450px;

      span {
        position: absolute;
        z-index: 1;
        font-size: 22px;
        color: #eee;
        font-weight: 800;
        padding: 20px 30px;
      }

      .man-bg {
        position: relative;
        width: 100%;
        height: 100%;
        border-radius: 16px;
        background-color: #1D1E1F;
        background-image: url('../img/man_silhouette.png');
        background-size: cover;
        background-position: 50% 50%;
        animation: fade-in .8s ease;
      }

      & .chart-card {
        transform: translateY(-100%);
        width: 100%;
        height: 100%;
      }
    }

    .persona-bar {
      width: 400px;

      & .chart-card {
        width: 100%;
        height: 100%;
      }
    }
  }
  
</style>
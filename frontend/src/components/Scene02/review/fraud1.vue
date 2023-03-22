<template>
    <div class="container">
        <div class="return">
            <img class="returnBtn" src="../../../img/导航栏/返回按钮.png" alt="" @click="re">
            <div class="returnTxt">
                返回
            </div>
        </div>
        <div class="info">
            <div class="chart1 chart">
                <span v-for="item in type"> {{ item }}</span>

            </div>
            <div class="chart2 chart">
                是否被骗： <span v-for="item in isTrue"> {{ item }}</span>
            </div>
            <div class="chart3 chart">
                <img :src="videoBtnPath" @click="changeVideo" alt="">
                <img :src="imgBtnPath" @click="changeImg" alt="">
            </div>
        </div>
        <div class="fraudBox">
            <img v-show="imgShow" class="Img" :src="titleImg" alt="">

            <video v-show="videoShow" class="video" controls>
                <source src="/video/防疫诈骗.mp4" type="video/mp4">


            </video>

        </div>

    </div>
</template>
<script setup lang='ts'>
import { ref} from 'vue';
import { useRouter } from 'vue-router';

import titleImg from '../../../img/scene02/review/防疫诈骗知识点.png';

const router = useRouter();
const re = () => {
    router.go(-1)
}

const fraud3 = JSON.parse(sessionStorage.fraud1);
const type = fraud3.fraudType;

const imgShow = ref<any>(false);
const videoShow = ref<any>(true);

// const vidSrc = 'src/audio/电信诈骗.mp4';
const videoBtnPath = ref('src/img/scene02/review/观看视频按钮已点击.png');
const imgBtnPath = ref('src/img/scene02/review/学习知识点按钮未点击.png');

const changeVideo = () => {
    if (!videoShow.value) {
        videoShow.value = true;
        imgShow.value = false;
        videoBtnPath.value = 'src/img/scene02/review/观看视频按钮已点击.png';
        imgBtnPath.value = 'src/img/scene02/review/学习知识点按钮未点击.png'
    }

}
const changeImg = () => {
    if (!imgShow.value) {
        videoShow.value = false;
        imgShow.value = true;
        videoBtnPath.value = 'src/img/scene02/review/观看视频按钮未点击.png';
        imgBtnPath.value = 'src/img/scene02/review/学习知识点按钮已点击.png'
    }

}

var isTrue = JSON.stringify(fraud3.fraudResult);
if (isTrue) {
    isTrue = "是"
} else {
    isTrue = "否"
}
console.log(isTrue)

</script>
<style scoped lang='scss'>
@font-face {
    font-family: "createfont";
    src: url("../../../font/造字工房言宋体.otf");
}

* {
    font-family: "createfont";
}










.container {
    background: url('../../..//img/scene02/review/复盘.png');
    position: absolute;
    background-size: cover;
    left: 0;
    background-color: rgb(228, 112, 112);
    // top: -5%;
    width: 100%;
    height: 106%;



}

.info {
    position: absolute;
    width: 300px;
    height: 690px;
    left: 23%;
    top: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    box-shadow: 0 0 15px rgb(168, 168, 168) inset;
    border-radius: 35px 0px 0 35px;

    .chart {
        margin-top: 15%;
        margin-left: 11%;
        font-size: 40px;
        text-shadow: 2px 2px 2px rgb(142, 193, 216), 0px 0px .6px rgb(204, 135, 45);
        color: rgb(250, 220, 128);
        text-align: left;

    }

    .chart1 {}

    .chart2 {
        font-size: 29px;
    }

    .chart3 {
        // font-size: 29px;
    // background-color: rgb(54, 45, 45);
    margin: 0 auto;
    text-align: center;
    margin-top: 87%;
    img{
        width: 80%;
        margin-top: 10px;
        cursor: pointer;
    }


    }
    
}

.fraudBox {
    position: absolute;
    width: 980px;
    height: 690px;
    left: 58%;
    top: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    box-shadow: 0 0 15px rgb(168, 168, 168) inset;
    border-radius: 0px 35px 35px 0;
    overflow: scroll;

    .video {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 95%;
        transform: translate(-50%, -50%);
    }
    .Img{
        margin-top: 6%;
        // background-color: red;
       
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
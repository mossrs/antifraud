<template>
    <div class="container" v-show="reviewShow">
        <div class="return">
            <img class="returnBtn" src="../../../img/导航栏/返回按钮.png" alt="" @click="re">
            <div class="returnTxt">
                返回
            </div>
        </div>
        <img id="police" src="../../../img/scene02/police2.png" alt="">
        <div class="mapbox">

            <img class="img1 img" :src="fraud1.fraudResult ? noImg : yesImg" alt="" @click="goto1">
            <img class="img2 img" :src="fraud2.fraudResult ? noImg : yesImg" alt="" @click="goto2">
            <img class="img3 img" :src="fraud3.fraudResult ? noImg : yesImg" alt="" @click="goto3">
            <img class="img4 img" :src="fraud4.fraudResult ? noImg : yesImg" alt="" @click="goto4">
            <img class="img5 img" :src="fraud5.fraudResult ? noImg : yesImg" alt="" @click="goto5">

            <div class="txt txt1">
                <span v-for="txt in fraud1.fraudType"> {{txt}}</span>
            </div>
            <div class="txt txt2">
                <span v-for="txt in fraud2.fraudType"> {{txt}}</span>
            </div>

            <div class="txt txt3">
                <span v-for="txt in fraud3.fraudType"> {{txt}}</span>
            </div>

            <div class="txt txt4">
                <span v-for="txt in fraud4.fraudType"> {{txt}}</span>
            </div>

            <div class="txt txt5">
                <span v-for="txt in fraud5.fraudType"> {{txt}}</span>
            </div>


            <!-- <div class="txt">45345</div>
            <div class="txt"></div>
            <div class="txt"></div> -->
        </div>
    </div>
</template>
<script setup lang='ts'>

import { ref } from 'vue'
import { useRouter } from 'vue-router';

import yesImg from '../../../img/scene02/yes.png';
import noImg from '../../../img/scene02/no.png';

const router = useRouter();



var fraud1 = JSON.parse(sessionStorage.fraud1); //新型诈骗
var fraud2 = JSON.parse(sessionStorage.fraud2);  //街头诈骗
var fraud3 = JSON.parse(sessionStorage.fraud3);   //泄露隐私
var fraud4 = JSON.parse(sessionStorage.fraud4); //刷单
var fraud5 = JSON.parse(sessionStorage.fraud5);  //电信

const goto1 = () => {
    router.push('/fraud1')
}
const goto2 = () => {
    router.push('/fraud2')
}

const goto3 = () => {
    router.push('/fraud4')
}
const goto4 = () => {
    router.push('/fraud3')
}
const goto5 = () => {
    router.push('/fraud5')
}
const reviewShow = ref(true)

const emit = defineEmits(["onReceiveReview"]);
const re = () => {
    // reviewShow.value = false;
    emit("onReceiveReview",false)

}



</script>


<style scoped lang='scss'>

.container{
    width: 100%;
    height: 100%;
    background-color: rgb(221,241,246);
}




@font-face {
    font-family: "createfont";
    src: url("../../../font/造字工房言宋体.otf");
}

* {
    font-family: "createfont";
}
  #police {
      position: absolute;
      left: 0;
      width: 25%;
      top: 30%;
      // display: inline;
      // z-index: -10;
  }
.mapbox{
    position: relative;
    width: 1200px;
    height: 75%;
   margin-left: 80px;
    background-color: rgb(114, 40, 40);
    margin-top: 6%;
    left: 50%;
    transform: translate(-50%);
    background-image: url('../../../img/scene02/map.jpg');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    border: 8px solid rgb(58, 147, 189);
    border-style: inset;
    border-radius: 25px;
  

    // .map{
    //     width: 100%;
    //     height: 100%;
    //     position: absolute;
    //     z-index: -100;
    //     left: 0;
    // }
    .img{
        position: absolute;
        width: 10%;
        cursor: pointer;
        // z-index: -100;
        left: 0;

    }
    .img1{
        margin-top: 50%;
        margin-left: 64%;
    }
    .img5 {   //电信
        margin-top: 13%;
        margin-left: 3%;
    }
    .img4 {   //刷单
        margin-top: 13%;
        margin-left: 67%;
    }
    .img3{   //泄露隐私
        margin-top: 44%;
        margin-left: 20%;
    }
    .img2 {   //接头
        margin-top: 20%;
        margin-left: 46.5%;
    }

    .txt{
        position: absolute;
        background-color: rgba(174, 217, 223, 0.897);
        padding: 10px 20px;
        border: 2px solid rgb(130, 186, 194);
        border-radius: 25px;
        font: optional;
        // width: 800px;
        // margin-top: 20%;
       
    }
    .txt1{
        margin-top: 43.8%;
            margin-left: 64%;
    }
    .txt2{
        margin-left: 46%;
            margin-top: 13%;
            z-index: 100;
    }
    .txt3{
        margin-left: 67%;
        margin-top:7%;

    }
    .txt4 {
        margin-left: 19%;
        margin-top: 38%;

    }
    .txt5 {
        margin-left: 3%;
        margin-top: 7%;

    }

   
}
.return {
    position: absolute;
    top: 3%;
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
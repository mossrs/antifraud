<template>
    <div class="top">
        <img class="NavbarImg" src="../../img/导航栏/导航栏.png" alt="">
        <img class="map" @click="openMap" src="../../img/导航栏/小图.png" alt="">
        <img class="IdImg" src="../../img/导航栏/导航栏头像.png" alt="">
        

        <div class="scene">{{data["scenename"]}}</div>
        <div class="money">{{data["money"]}}</div>
    </div>
    <div class="main" v-show="mapShow">
        <img  src="../../img/导航栏/tips.png" @click="closeMap" alt="">
    </div>
</template>
<script setup lang='ts'>
import { ref, onMounted } from 'vue';

const re = (() => {

})


type dataProps = {
    data: string[];
}
// defineProps<{

// }>()

defineProps<{
    data
}>();

const mapShow = ref(true);
const openMap = () => {
    mapShow.value = true;
}

const closeMap = () => {
    mapShow.value = false;
    
}


</script>
<style scoped lang='scss'>
    @font-face {
        font-family: 'creatFont';
        src: url('../../font/造字工房言宋体.otf');
    }
    *{
        font-family: 'creatFont';
    }
.top {
    height: 10%;
    width: 100%;
    // height: 20%;
    position: relative;
    padding: 0;
    z-index: 100;
    // background-color: #fff;

    .NavbarImg {
        position: absolute;
        // display: block;
        left: 50%;
        transform: translate(-50%);
        top: -6%;
        width: 100.5%;
        // height: 49%;
    }

    .map {
        position: absolute;
        left: 3.88%;
        top: -8%;
        // width: 8.3%;
        // height: 50.5%;
        cursor: pointer;
    }

    .IdImg {
        position: absolute;
        right: 4.3%;
        top: 17%;
        width: 3.3%;
        cursor: pointer;

    }

  
    .scene{
        position: absolute;
        left: 42%;
      
        top:26.9%;
        font-size: 1.9rem;
        font-weight: 700;
        color: rgb(97, 97, 97);
        // background-color:rgb(240,224,159);
        width: 16%;
        padding: 3px 0;
   
    }
        .money{
        position: absolute;
        left: 80%;
        padding: 8px 0;
        top: 21%;
        font-size:1.9rem;
        font-weight: 700;
        color: rgb(255, 255, 255);
        }
}
.main{
    cursor: pointer;
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    z-index: 10;
   
}
</style>
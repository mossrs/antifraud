<template>

    <div id="box">
<img id="police" src="../../img/scene02/police.png" alt="">
        <ul v-if="boxShow">
            <li @click="goto1" class="li1 li">

                <div :class="['Div', 'Div1', showText ? 'hidden' : '']">

                </div>
                <div class="mask" v-show="showText"></div>
                <img class="txt" src="../../img/scene02/复盘.png" v-show="showText" alt="">



            </li>
            <li @click="goto2" class="li li2">
                <div :class="['Div', 'Div2', showText ? 'hidden' : '']">

                </div >
                <div class="mask" v-show="showText"></div>
                <img class="txt" src="../../img/scene02/测一测.png" v-show="showText" alt="">
            </li>
            <li @click="goto3" class="li li3">
                <div :class="['Div', 'Div3', showText ? 'hidden' : '']">

                </div>
                <div class="mask" v-show="showText"></div>
                <img class="txt" src="../../img/scene02/玩一玩.png" v-show="showText" alt="">
            </li>
            <li class="li li4" @click="goto4">
                <div :class="['Div', 'Div4', showText ? 'hidden' : '']">

                </div>
                <div class="mask" v-show="showText"></div>
                <img class="txt" src="../../img/scene02/个人画像.png" v-show="showText" alt="">
            </li>
        </ul>
        <Game @onReceiveGame = 'onReceiveGame' v-if="gameShow" />
        <Test @onReceiveTest = 'onReceiveTest' v-if="testShow" />
        <Review @onReceiveReview = 'onReceiveReview' v-if="reviewShow" />
    </div>


</template>
<script setup lang='ts'>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import gsap from 'gsap';
import Review from './review/review.vue';
import Game from './game.vue';
import Test from './test.vue';



const boxShow = ref(true);
const gameShow = ref(false);
const testShow = ref(false);
const reviewShow = ref(false);
const showText = ref(true);
const router = useRouter();

 
//接收组件
const onReceiveGame = (params: boolean) => {
    gameShow.value = params;
    boxShow.value = !params;
    showText.value = true;
    console.log(params)
}

const onReceiveTest = (params: boolean) => {
    testShow.value = params;
    boxShow.value = !params;
    showText.value = true;
    console.log(params)
}


const onReceiveReview = (params: boolean) => {
    reviewShow.value = params;
    boxShow.value = !params;
    showText.value = true;
    console.log(params)
}
;
const goto1 = () => {
    const mytween1 = gsap.timeline();
    const mytween2 = gsap.timeline();
   
    showText.value = false;
    
    let li: any = document.getElementsByClassName("li");
    let liCurrent: any = document.getElementsByClassName("li1")[0];
    for (let i = 0; i < li.length; i++){
       
        if (li[i] !== liCurrent) {
            mytween1.to(li[i], .18, { width: 0+'%' })
        }
        
            }
    mytween2
        .to(liCurrent, .9, { width: 100 + '%' })
        .to(boxShow, { value: false })
        .to(reviewShow, { value: true })

    
    // setTimeout(() => {
    //     router.push('/review');
    // },500)
    
}
const goto2 = () => {
    const mytween3 = gsap.timeline();
    const mytween4 = gsap.timeline();
    showText.value = false;
    let li: any = document.getElementsByClassName("li");
    let liCurrent: any = document.getElementsByClassName("li2")[0];
    for (let i = 0; i < li.length; i++) {

        if (li[i] !== liCurrent) {
            mytween3.to(li[i], .18, { width: 0 + '%' })
        }

    }

        mytween4
            .to(liCurrent, .9, { width: 100 + '%' })
            .to(boxShow, { value: false })
            .to(testShow, { value: true })

    

}
const goto3 = () => {
    const mytween5 = gsap.timeline();
    const mytween6 = gsap.timeline();

    showText.value = false;

    let li: any = document.getElementsByClassName("li");
    let liCurrent: any = document.getElementsByClassName("li3")[0];
    for (let i = 0; i < li.length; i++) {

        if (li[i] !== liCurrent) {
            mytween5.to(li[i], .1, { width: 0 + '%' })
        }

    }
    mytween6       
        .to(liCurrent, .8, { width: 100 + '%' })
        .to(boxShow, { value: false })
        .to(gameShow, { value: true })
   
    // router.push('/game')box
}

const goto4 = () => {
    const mytween5 = gsap.timeline();
    const mytween6 = gsap.timeline();

    showText.value = false;

    let li: any = document.getElementsByClassName("li");
    let liCurrent: any = document.getElementsByClassName("li4")[0];
    for (let i = 0; i < li.length; i++) {

        if (li[i] !== liCurrent) {
            mytween5.to(li[i], .1, { width: 0 + '%' })
        }

    }
    mytween6       
        .to(liCurrent, .8, { width: 100 + '%' })
        .to(boxShow, { value: false })
        .call(() => { setTimeout(() => boxShow.value = showText.value = true, 200) })
        .call(() => { router.push('persona') });
   
    // router.push('/game')box
}


</script>
<style scoped lang='scss'>

    #box{
        width: 100%;
        height: 100%;
        background-color: rgb(221,241,246);
        #police{
            position: absolute;
            left: 0;
            width: 30%;
            top: 30%;
            // display: inline;
            // z-index: -10;
        }
      
    }
    ul{
        position: absolute;
        width: 1200px;
        height: 75%;
        // background-color: rgb(77, 74, 74);
        margin: 0 auto;
        margin-top:6% ;
        display: flex;
        // margin-left: 30%;
        left: 50%;
        transform: translate(-50%);
        justify-content: space-between;
       


    }
    li{
        list-style: none;
        // background-color: rgb(82, 76, 76);
        width: 20%;
        height: 100%;
        position: relative;
        border: 8px solid rgb(58, 147, 189);
        border-style: inset;
        border-radius: 25px;
        cursor: pointer;

        &:hover {
            .Div {
                opacity: 1 !important;
            }
            .mask {
                opacity: 0;
            }
            .txt {
                opacity: 0;
            }
        }
      
        .Div{
            
            background-repeat: no-repeat;
            background-position: center;
            width: 100%;
            height: 100%;
            min-height: 700px;
            transition: opacity .3s ease;
            border-radius: 25px;
            position: absolute;
           
            &.hidden {
                opacity: .2;
            }
            
        }
        .Div:hover{
         
                opacity: 1;
                
  
            
            
            
        }
        .Div1{
            background-image: url("../../img/scene02/map2.png");
        }
        .Div2{
            background-image: url("../../img/scene02/test.jpg");
        }
        .Div3 {
            background-image: url("../../img/scene02/game.jpg");
        }
        .Div4 {
            background-image: url("../../img/scene02/个人画像.jpg");
            background-position: 43.5%;
        }

       .mask {
        width: 100%;
        height: 100%;
        background-color: rgba(255, 255, 255, .2);
        backdrop-filter: blur(4px);
        transition: opacity .3s ease;
       }

    }

.txt{
    width: 45%;
    // margin-top: 50%;
    position: absolute;
    display: block;
    margin: 0 auto;
    left: 50%;
    top: 50%;
    opacity: 1;
    transform: translate(-50%,-50%);
    z-index: 100;
    transition: opacity .3s ease;
}

</style>
<template>
    <div>
        <div class="return">
            <img class="returnBtn" src="../../img/导航栏/返回按钮.png" alt="">
            <div class="returnTxt">
                返回
            </div>
        </div>


        <div id="box"></div>
        <div class="poppup" v-show="showPop">

            <img id="bg2" :src="bgUrl" alt="">
            <img id="dialoImg" :src="path" alt="" @click="changeText">
            <div class="d1">你好学弟，请问有什么需要帮忙的吗？我是你学长，有什么都可以找我帮忙哦~</div>
            <img class="nextBtn" src="../../img/scene01/dialogBox/按钮.png" alt="" @click="changeText">
            <div v-show="chooseBtn1" class="chooseBtn">

                <div class="cc c1" @click="changeText">
                    谢谢学长，不用麻烦啦，我可以自己完成报道
                </div>

                <div class="cc c2" @click="changeText">
                    好的呀，谢谢学长！
                </div>
            </div>
            <div v-show="chooseBtn2" class="chooseBtn">

                <div class="cc c1" @click="changeText">
                    盛情难却，刚好可以进群认识更多朋友
                </div>

                <div class="cc c2" @click="changeText">
                    学长不用了，我还是先走了吧
                </div>
            </div>
            <div v-show="chooseBtn3" class="chooseBtn">

                <div class="cc c1" @click="buyCard">
                    好像确实可以省钱，那我赶紧买一张吧
                </div>

                <div class="cc c2" @click="exict2">
                    先不买了，等开学再说吧
                </div>
            </div>


        </div>

        <AtmScene @onReceiveMsg="onReceiveMsg" @onReceiveATMMoney="onReceiveATMMoney" v-if="showATM" />
        <DormScene @onReceiveMsgDom="onReceiveMsgDom" @onReceiveMsgDormMoney="onReceiveDormMoney" v-if="showDorm">
        </DormScene>
        <LibraryScene @onReceiveMsgLibrary="onReceiveMsgLibrary" @onReceiveMsgLibraryMoney="onReceiveLibraryMoney"
            v-if="showLibrary">
        </LibraryScene>
        <Navabar :data="datalist" />

    </div>







</template>
<script setup lang='ts'>
// import { PIXI } from 'pixi.js'
import * as PIXI from 'pixi.js';
import { onMounted, reactive, getCurrentInstance } from 'vue';
import { ref } from 'vue';
import { gsap } from 'gsap';
import { ElMessage } from 'element-plus';

import AtmScene  from '../s1-composition/atmScene.vue';
import DormScene from '../s1-composition/dormScene.vue';
import LibraryScene from '../s1-composition/libraryScene.vue';
// var people = document.getElementById("people");
import { setPersonaData } from '../../hooks';

import Navabar from '../s1-composition/Navabar.vue';

import bgUrl from '../../img/scene01/场景2宿舍楼下.png';
import character0 from '../../img/scene01/dialogBox/学长.png';
import character1 from '../../img/scene01/dialogBox/主角.png';

type dataType = {
    scenename: string,
    money: string
};

const datalist = reactive<dataType>({
    scenename: "校园内",
    money: sessionStorage.getItem("money") || ''
})



const mytimeIn1 = ref(0);
const mytimeOut1 = ref(0);

type FraudData = {
    fraudResult: boolean;
    fraudType: string;
    hesitateTime: number;
}
const form = reactive<FraudData>({
    fraudResult: true,
    fraudType: "街头诈骗",
    hesitateTime: 0,     /*犹豫时间 单位毫秒*/
});


const path = ref(character0);


var speed = 10;

var y = 0;
var x = 0;


const text = [
    "谢谢学长,我没问题的",
    "不用客气的，你现在报道了吗？没报道需要先去第一教学楼报道哦，对了你是哪个学院的什么专业的呢？我可以直接告诉你楼层",
    "我是***学院***专业的，正准备去报道呢",
    "那么巧吗？我也是**学院**专业的，看来真的是你学长了，那我一会陪你去报道吧",
    "......",
    "不用客气，都是直系学长了，我们专业很强的，就业率高，校友资源也很好，对了学弟你是哪里人，我听你口音很像我老乡。",
    "学长，我是**人",
    "怎么会有这么巧的事！我也是**人，那我刚好可以拉你进我们专业的老乡群，这也太巧了，以后好好照顾你！你先加我微信，我再拉你进群。",
    "......",
    "对啦，我还得嘱咐你一句，每年学校都很坑的，开学后要你们买600块一张的上机卡，一定要这个卡才能进机房，但是我们都觉得太贵了。",
    "这么贵？有什么办法吗",
    "所以不要花冤枉钱，我们学长学姐这边有老师之前给的内部卡，你看群里都有网页和链接，只要400块，就是为了不让大家掉进这个坑，你可以抓紧拿一张，我也不保证还剩多少了",
    "......"
]
var key = 0;
var xiaobin = false;



const showPop = ref(false);  //对话框显示

const showDorm = ref(false);  //对话框显示
const chooseBtn1 = ref(false);  //对话框显示
const chooseBtn2 = ref(false);  //对话框显示
const chooseBtn3 = ref(false);  //对话框显示
const showATM = ref(false);  //对话框显示
// const arrow2 = ref(true);  //对话框显示

const showLibrary = ref(false);

const exict2 = () => {

    showPop.value = false;
    // tt();
    mytimeOut1.value = +new Date;
    form.fraudResult = false;
    form.hesitateTime = mytimeOut1.value - mytimeIn1.value;
 
    let b = JSON.parse(JSON.stringify(form));
    setPersonaData({data:b})
        .then(res => {
            console.log(res.data);
            console.log(b);
        }).catch(() => ElMessage("网络异常，请稍后再试"))
    sessionStorage.setItem("fraud2", JSON.stringify(form));
    // tt();
}
const buyCard= () => {

    showPop.value = false;
    mytimeOut1.value = +new Date;
    form.fraudResult = true;
    form.hesitateTime = mytimeOut1.value - mytimeIn1.value;
    const b = JSON.parse(JSON.stringify(form));
    setPersonaData({data:b})
        .then(res => {
        console.log(res)
        }).catch(() => ElMessage("网络异常，请稍后再试"))

    datalist.money = JSON.stringify(JSON.parse(sessionStorage.getItem("money") || '') - 400);
    sessionStorage.setItem("money", datalist.money);
    sessionStorage.setItem("fraud2", JSON.stringify(form));
    console.log(sessionStorage)
    // tt();
}




function hitTestRectangle(r1, r2) {

    //Define the variables we'll need to calculate
    let hit, combinedHalfWidths, combinedHalfHeights, vx, vy;
    var hitLeft, hitRight, hitTop, hitBottom;
    hitLeft = false;
    hitRight = false;
    hitTop = false;
    hitBottom = false;

    //hit will determine whether there's a collision


    //Find the center points of each sprite
    r1.centerX = r1.x + r1.width / 2;
    r1.centerY = r1.y + r1.height / 2;
    r2.centerX = r2.x + r2.width / 2;
    r2.centerY = r2.y + r2.height / 2;

    //Find the half-widths and half-heights of each sprite
    r1.halfWidth = r1.width / 2;
    r1.halfHeight = r1.height / 2;
    r2.halfWidth = r2.width / 2;
    r2.halfHeight = r2.height / 2;

    //Calculate the distance vector between the sprites
    vx = r1.centerX - r2.centerX;
    vy = r1.centerY - r2.centerY;

    //Figure out the combined half-widths and half-heights
    combinedHalfWidths = r1.halfWidth + r2.halfWidth;
    combinedHalfHeights = r1.halfHeight + r2.halfHeight;

    //Check for a collision on the x axis
    if (Math.abs(vx) < combinedHalfWidths) {

        //A collision might be occurring. Check for a collision on the y axis
        if (Math.abs(vy) < combinedHalfHeights) {
            if (r2.centerX > r1.centerX && r2.centerY > r1.centerY) {



                hit = true;
            }

        } else {

            //There's no collision on the y axis
            hit = false;

        }
    } else {

        //There's no collision on the x axis
        hit = false;

    }

    //`hit` will be either `true` or `false`
    return [hitLeft, hitRight, hitTop, hitBottom];
};
const changeText = () => {

    document.getElementsByClassName("d1")[0].innerHTML = text[key];

    if (xiaobin) {
        path.value = character0;
        xiaobin = false;
    } else {
        path.value = character1;

        xiaobin = true;
    }
    if (key == 4) {
        chooseBtn1.value = true;
    } else if (key == 8) {
        chooseBtn2.value = true;
    } else if (key == 12) {
        chooseBtn3.value = true;
        mytimeIn1.value = +new Date;
    }
    else {
        chooseBtn1.value = false;
        chooseBtn2.value = false;
        chooseBtn3.value = false;
    }

    if (key == (text.length - 1)) {
        key = key;

    } else {
        key += 1;
    }
}







// nextTick(() => {
//     const com = ref();
//     console.log(com.value.msg1);
// })
const onReceiveATMMoney = (params: string) => {
    datalist.money = params;
}
const onReceiveDormMoney = (params: string) => {
    datalist.money = params;
}
const onReceiveLibraryMoney = (parms: string) => {
    datalist.money = parms;
}

const receiveMsg = ref(true);
const onReceiveMsg = (params: boolean) => {
    console.log("接收自atm组件的值", params);
    receiveMsg.value = params;
}

const receiveMsgDom = ref(true);
const onReceiveMsgDom = (params: boolean) => {
    console.log("接收自宿舍组件的值", params);
    receiveMsgDom.value = params;
}

const receiveMsgLibrary = ref(true);
const onReceiveMsgLibrary = (params: boolean) => {
    console.log("接收自图书馆组件的值", params);
    receiveMsgLibrary.value = params;
}
let currentInstance;
onMounted(() => {
    currentInstance = getCurrentInstance();


    var app = new PIXI.Application({
        // width: 10,         // default: 800 宽度
        // height: 100,        // default: 600 高度
        antialias: true,    // default: false 反锯齿
        transparent: false, // default: false 透明度
        resolution: 1,      // default: 1 分辨率
        // forceCanvas: true
    });

    document.getElementById("box")!.appendChild(app.view);



    //画布尺寸
    app.renderer.view.style.position = "absolute";
    app.renderer.view.style.display = "block";
    app.renderer.backgroundColor = 0xffffff0;
    // app.renderer.autoResize = true;
    app.renderer.resize(window.innerWidth, window.innerHeight);

    const bgTexture = PIXI.Texture.from("/pixi_res/scene01/map/地面.png");
    const bg = new PIXI.Sprite(bgTexture);
    app.stage.addChild(bg);


    // console.log()

    //加载背景图

    //map
    const texturePlayground = PIXI.Texture.from("/pixi_res/scene01/map/左上角.png");
    const textureDormitory = PIXI.Texture.from("/pixi_res/scene01/map/右上角.png");
    const textureTeachingBuilding = PIXI.Texture.from("/pixi_res/scene01/map/左侧.png");
    const textureOfficel = PIXI.Texture.from("/pixi_res/scene01/map/右侧.png");
    // const textureLeftBottom = PIXI.Texture.from("/pixi_res/scene01/map/左下角.png");
    //   const textureAdministrator = PIXI.Texture.from("/pixi_res/scene01/map/右上角.png");

    //人物行走
    const textureRight1 = PIXI.Texture.from("/pixi_res/people/left1.png");
    const textureRight2 = PIXI.Texture.from("/pixi_res/people/left2.png");
    const textureLeft1 = PIXI.Texture.from("/pixi_res/people/right1.png");
    const textureLeft2 = PIXI.Texture.from("/pixi_res/people/right2.png");
    const textureBack1 = PIXI.Texture.from("/pixi_res/people/back1.png");
    const textureBack2 = PIXI.Texture.from("/pixi_res/people/back2.png");
    const textureFront1 = PIXI.Texture.from("/pixi_res/people/front1.png");
    const textureFront2 = PIXI.Texture.from("/pixi_res/people/front2.png");
    // let sprite = new PIXI.Sprite(texture);
    let textureLeft = [textureLeft1, textureLeft2];
    let textureRight = [textureRight1, textureRight2];
    let textureFront = [textureFront1, textureFront2];
    let textureBack = [textureBack1, textureBack2];


    //学长学姐    //热情的学长学姐
    const textureClassman = PIXI.Texture.from("/pixi_res/people/学长.png")
    const upperclassman = new PIXI.Sprite(textureClassman);

    //提示标
    const texturearrow = PIXI.Texture.from("/pixi_res/scene01/提示标.png")
    //进入宿舍按钮
    const intoDorm = new PIXI.Sprite(texturearrow)
    //进入ATM机器
    const ATM = new PIXI.Sprite(texturearrow);
    //进入学长
    const arrow1 = new PIXI.Sprite(texturearrow);
    //进入图书馆
    const library = new PIXI.Sprite(texturearrow)

    //圆圈
    const textureCircle = PIXI.Texture.from("/pixi_res/scene01/circle.png")
    const circle1 = new PIXI.Sprite(textureCircle);
    const circle2 = new PIXI.Sprite(textureCircle);
    const circle3 = new PIXI.Sprite(textureCircle);
    const circle4 = new PIXI.Sprite(textureCircle);

    //障碍物
    const people = new PIXI.AnimatedSprite(textureFront);
    const Playground = new PIXI.Sprite(texturePlayground);
    const Dormitory = new PIXI.Sprite(textureDormitory);
    const TeachingBuilding = new PIXI.Sprite(textureTeachingBuilding);
    // const LeftBottom = new PIXI.Sprite(textureLeftBottom);
    const Officel = new PIXI.Sprite(textureOfficel);

    people.animationSpeed = 0.1;


    // console.log(people.texture["textureCacheIds"][0] = "/pixi_res/people/right2.png");
    people.scale.set(.16);
    people.x = 1500;
    people.y = 820;
    people.anchor.x = 0.5;
    people.anchor.y = 0.2;

    //图书馆
    Playground.height = 400;
    Playground.width = 420;
    Playground.x = 0;
    Playground.y = 60;
    //宿舍
    Dormitory.height = 430;
    Dormitory.width = 1500;
    // people2.width = 160;
    Dormitory.x = 280;
    Dormitory.y = 60;
    //教学楼
    TeachingBuilding.x = 130;
    TeachingBuilding.y = 560;
    TeachingBuilding.width = 690;
    TeachingBuilding.height = 430

    //右边
    Officel.x = 1470;
    Officel.y = 220;
    //热情的学长
    upperclassman.scale.set(.16)

    upperclassman.x = 900;
    upperclassman.y = 380;

    arrow1.x = 1045;
    arrow1.y = 460;
    arrow1.anchor.x = 0.5;
    arrow1.anchor.y = 0.5;


    circle1.anchor.x = 0.5;
    circle1.anchor.y = 0.5;
    circle1.x = 1045;
    circle1.y = 507;

    //ATm

    ATM.anchor.x = 0.5;
    ATM.anchor.y = 0.5;
    ATM.x = 480;
    ATM.y = 920;
    circle2.anchor.x = 0.5;
    circle2.anchor.y = 0.5;
    circle2.x = 480;
    circle2.y = 965;



    //进入宿舍按钮
    intoDorm.x = 1387;
    intoDorm.y = 370;
    intoDorm.anchor.x = 0.5;
    intoDorm.anchor.y = 0.5;


    //图书馆
    library.anchor.set(0.5, 0.5);
    circle4.anchor.set(0.5, 0.5);
    library.x = 140;
    library.y = 396;
    circle4.x = 140;
    circle4.y = 440

    circle3.anchor.x = 0.5;
    circle3.anchor.y = 0.5;
    circle3.x = 1387;
    circle3.y = 405;

    //进入图书馆按钮
    //花圃
    const gardenTextute = PIXI.Texture.from('/pixi_res/scene01/map/garden.png');
    const garden = new PIXI.Sprite(gardenTextute);
    garden.x = 1050;
    garden.y = 579;
    garden.scale.set(.75)

//校门口
    const schoolGateTexture = PIXI.Texture.from('/pixi_res/scene01/map/schoolGate.png')
    const gate = new PIXI.Sprite(schoolGateTexture);
    gate.x = 840;
    gate.y = 950;
    // gate.scale.set(.8)

    // let obstruction = new PIXI.Container();
    // obstruction.addChild(Playground);
    // obstruction.addChild(Dormitory);
    // obstruction.addChild(TeachingBuilding);
    // obstruction.addChild(LeftBottom)
    // obstruction.addChild(Officel)
    app.stage.addChild(
        Playground,
        Dormitory,
        Officel,
        circle1,
        circle2,
        circle3,
        circle4,
        arrow1,
        upperclassman,


        TeachingBuilding,
        ATM,
        intoDorm,
        library,
        gate,
        garden,
        people,
       

        // LeftBottom,


    )



    var flag1 = 0;
    var flag2 = 0;
    var flag3 = 0;
    var flag4 = 0;
    const fangda11 = gsap.timeline();
    const fangda111 = gsap.timeline();
    const fangda1111 = gsap.timeline();
    const fangda11111 = gsap.timeline();
    const fangda1 = gsap.timeline();
    const fangda2 = gsap.timeline();
    const fangda3 = gsap.timeline();
    const fangda4 = gsap.timeline();
    const fangda5 = gsap.timeline();
    fangda11.to(circle1.scale, 1.2, { x: 1.6, y: 1.6 })
        .to(circle1.scale, 1.2, { x: 1, y: 1 })
        .repeat(300)

    fangda111.to(circle2.scale, 1.2, { x: 1.6, y: 1.6 })
        .to(circle2.scale, 1.2, { x: 1, y: 1 })
        .repeat(300)

    fangda1111.to(circle3.scale, 1.2, { x: 1.6, y: 1.6 })
        .to(circle3.scale, 1.2, { x: 1, y: 1 })
        .repeat(300)
    fangda11111.to(circle4.scale, 1.2, { x: 1.6, y: 1.6 })
        .to(circle4.scale, 1.2, { x: 1, y: 1 })
        .repeat(300)
    fangda1.to(arrow1, 1.2, { y: 480 })
        .to(arrow1, 1.2, { y: 460 })
        // .to(arrow1, 1.2, { y: 460 })
        .repeat(300)
    fangda2.to(intoDorm, 1.2, { y: 380 })
        .to(intoDorm, 1.2, { y: 370 })
        // .to(arrow1, 1.2, { y: 460 })
        .repeat(300)
    fangda3.to(library, 1.2, { y: 416 })
        .to(library, 1.2, { y: 396 })
        // .to(arrow1, 1.2, { y: 460 })
        .repeat(300)
    fangda4.to(ATM, 1.2, { y: 940 })
        .to(ATM, 1.2, { y: 920 })
        // .to(arrow1, 1.2, { y: 460 })
        .repeat(300)


    document.addEventListener('keydown', function (e) {

        //热情的学长
        if (
            (people.x < arrow1.x + 20 && people.x > arrow1.x - 20) &&
            (people.y < arrow1.y + 40 && people.y > arrow1.y - 40)

        ) {
            if (flag1 == 0) {


                var myTween = gsap.to(app.stage.scale, 2, { x: 2, y: 2, });
                var myTween = gsap.to(app.stage, 2, { x: -1200, y: -600 });

                setTimeout(function () {
                    showPop.value = true;
                    flag1 = 1;
                    datalist.scenename = "热情的学长";
                }, 1600);
            } else {
                if ((!showPop.value) && (flag1 == 1)) {
                    var myTween1 = gsap.fromTo(app.stage.scale, 2, { x: 2, y: 2, }, { x: 1, y: 1, });
                    var myTween2 = gsap.to(app.stage, 2, { x: -0, y: 0 });
                    flag1 = 2;
                    app.stage.removeChild(arrow1, circle1);
                    arrow1.x = 0;
                    arrow1.y = 0;
                    go();
                    datalist.scenename = "校园内";
                }
            }
        } else {
            go();
        };

        //atmatmatmatmatmatn

        if (
            (people.x < ATM.x + 40 && people.x > ATM.x - 40) &&
            (people.y < ATM.y + 40 && people.y > ATM.y - 40)

        ) {
            if (flag2 == 0) {
                var myTween = gsap.to(app.stage.scale, 2, { x: 2, y: 2, });
                var myTween = gsap.to(app.stage, 2, { x: -20, y: -900 });
                setTimeout(function () {
                    showATM.value = true;
                    flag2 = 1;
                    datalist.scenename = "ATM机";
                    console.log("1111111", !receiveMsg.value, flag2);
                }, 1600);
            } else {
                if ((!receiveMsg.value) && (flag2 == 1)) {
                    var myTween11 = gsap.fromTo(app.stage.scale, 2, { x: 2, y: 2, }, { x: 1, y: 1, });
                    var myTween22 = gsap.to(app.stage, 2, { x: 0, y: 0 });
                    flag2 = 2;
                    app.stage.removeChild(ATM, circle2);
                    ATM.x = 0;
                    ATM.y = 0;
                    go();
                    datalist.scenename = "校园内";
                    console.log("000000", receiveMsg.value, flag2);
                }

            }

        } else {
            go();

        };



        //intointointointoDOrmDorm

        if (
            (people.x < intoDorm.x + 20 && people.x > intoDorm.x - 20) &&
            (people.y < intoDorm.y + 70 && people.y > intoDorm.y - 40)

        ) {
            if (flag3 == 0) {
                var myTween = gsap.to(app.stage.scale, 2, { x: 2, y: 2, });
                var myTween = gsap.to(app.stage, 2, { x: -1700, y: -200 });
                setTimeout(function () {
                    showDorm.value = true;
                    flag3 = 1;
                    datalist.scenename = "宿舍内";
                }, 1600);
            } else {
                if ((!receiveMsgDom.value) && (flag3 == 1)) {
                    var myTween1 = gsap.to(app.stage.scale, 2, { x: 1, y: 1, });
                    var myTween2 = gsap.to(app.stage, 2, { x: -0, y: 0 });
                    flag3 = 2;
                    app.stage.removeChild(intoDorm, circle3);
                    intoDorm.x = 0;
                    intoDorm.y = 0;
                    datalist.scenename = "校园内";
                    go();
                }

            }

        } else {
            go
        };


        //librarylibrarylibrary

        if (
            (people.x < library.x + 40 && people.x > library.x - 40) &&
            (people.y < library.y + 40 && people.y > library.y - 40)

        ) {
            if (flag4 == 0) {
                var myTween = gsap.to(app.stage.scale, 2, { x: 2, y: 2, });
                var myTween = gsap.to(app.stage, 2, { x: -20, y: -300 });
                setTimeout(function () {
                    showLibrary.value = true;
                    flag4 = 1;
                    datalist.scenename = "图书馆";
                    console.log("1111111", !receiveMsgLibrary.value, flag4);
                }, 1600);
            } else {
                if ((!receiveMsgLibrary.value) && (flag4 == 1)) {
                    var myTween11 = gsap.fromTo(app.stage.scale, 2, { x: 2, y: 2, }, { x: 1, y: 1, });
                    var myTween22 = gsap.to(app.stage, 2, { x: 0, y: 0 });
                    flag4 = 2;
                    app.stage.removeChild(library, circle2);
                    library.x = 0;
                    library.y = 0;
                    go();
                    datalist.scenename = "校园内";
                    console.log("000000", receiveMsgLibrary.value, flag4);
                }

            }

        } else {
            go();

        };


        function go() {
            x = 0;
            y = 0;
            if ((e.keyCode == 87 || e.keyCode == 38)) {   //上

                if (people.textures != textureBack) {
                    people.textures = textureBack
                }

                people.play();
                y = -speed;
                x = 0;

            }

            if (e.keyCode == 83 || e.keyCode == 40) {   //下
                if (people.textures != textureFront) {
                    people.textures = textureFront;
                }


                people.play();
                y = speed;
                x = 0;


            }

            if (e.keyCode == 68 || e.keyCode == 39) {  //you
                if (people.textures != textureLeft) {
                    people.textures = textureLeft;
                }


                people.play();
                x = speed;
                y = 0;

            }

            if (e.keyCode == 65 || e.keyCode == 37) {  //zuo
                if (people.textures != textureRight) {
                    people.textures = textureRight;
                }


                people.play();
                x = -speed;
                y = 0;

            }

            people.x += x;
            people.y += y;



        }
    });




    document.addEventListener('keyup', function () {
        people.stop();
        // hitLeft = false;
        // hitRight = false;
        // hitTop = false;
        // hitBottom = false;

        // app.ticker.remove(delta => gameLoop(delta))
    });

    // setInterval(function () {


    //         .to('.msg', .08, { rotateZ: 3.5, })
    //         .repeat(6)
    // }, 1700);




})








</script>
<style lang='scss'>
@font-face {
            font-family: 'creatFont';
            src: url('../../font/造字工房言宋体.otf');
        }
.poppup {


   
    // position: relative;

    #dialoImg {
        animation: popup .5s ease-in-out;
        position: absolute;
        left: 3%;
        // width: 85%;
        bottom: 0;
        cursor: pointer;
    }
    .d1{
        animation: popup .5s ease-in-out;
    }

    #bg2 {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0;
    }



    .d1 {
        position: absolute;
        z-index: 100;
        // height: 200px;
        width: 1200px;
        // background-color: rgba(182, 95, 95, 0.416);

        left: 39%;

        transform: translate(-50%);
        top: 73%;
        text-align: left;
        // font-family: "微软雅黑";
        font-size: 28px;
        letter-spacing: 2px;
        animation: test .5s ease-in-out;
        text-indent: 60px;
        font-family: 'creatFont';
        line-height: 39px;
    }

    .nextBtn {
        position: fixed;
        bottom: 30px;
        right: 8%;
        cursor: pointer;
    }

    .chooseBtn {
        position: fixed;
        bottom: 37px;
        right: 15%;
        text-align: center;

        .cc {

            // width: 170px;
            height: 40px;
            line-height: 40px;
            margin-left: 40px;
            border-radius: 15px;
            display: inline-block;
            background-color: rgb(245, 253, 255);
            border: 1px solid rgb(165, 211, 225);
            cursor: pointer;
            padding: 6px 10px;

        }

       

    }
}

@keyframes popup {
    0% {
        width: 0%;
        height: 0%;

    }

    25% {
        width: 25%;
        height: 25%;
    }

    50% {
        width: 50%;
        height: 50%;
    }

    100% {
        width: 100%;
        height: 100%;
    }

}



@keyframes test {
    0% {
        opacity: 0;

        // width: 25%;
        // width: 100%;
        // height: 25%;
    }

    50% {
        opacity: .3;
        // width: 100%;
        // width: 50%;
        // height: 50%;
    }

    100% {
        opacity: 1;
        // width: 100%;
        // height: 100%;
    }

}





li {
    list-style: none;
}

// .circle {
//     position: absolute;
//     border: 2px solid;
//     width: 60px;
//     height: 30px;
//     left: 53%;
//     border-radius: 100%;
//     top: 50%;
//     // z-index: -1;

// }
.return {
    position: absolute;
        top: 9.7%;
        // left: 20%;
        left: 1%;
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
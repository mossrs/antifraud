<template>
<div id="box1">

</div>
</template>
<script setup lang='ts'>
import * as PIXI from 'pixi.js';
import { SpriteMaskFilter } from 'pixi.js';
import { onMounted } from 'vue';
const emit = defineEmits(["onReceiveGame"]);
onMounted(() => {
    var app = new PIXI.Application({
        width: 1906,         // default: 800 宽度
        height: 1130,        // default: 600 高度
        antialias: true,    // default: false 反锯齿
        transparent: false, // default: false 透明度
        resolution: 1,      // default: 1 分辨率
        // forceCanvas: true
    })

    document.getElementById("box1")?.appendChild(app.view);
    //画布尺寸
    app.renderer.view.style.position = "absolute";
    app.renderer.view.style.display = "block";
    app.renderer.backgroundColor = 0xffffff0;
    // app.renderer.autoResize = true;
    // app.renderer.resize(window.innerWidth, window.innerHeight);

    //背景图
    const backgroundImageTexture = PIXI.Texture.from("/pixi_res/scene02/game/跑酷.png");
    const backgroundImage = new PIXI.Sprite(backgroundImageTexture);
    backgroundImage.width = 1960;
    // backgroundImage.height = 1080;

    //主角
    const boyTexture = PIXI.Texture.from("/pixi_res/people/back1.png");
    const boy = new PIXI.Sprite(boyTexture);
    boy.anchor.set(.5, .5);
    boy.scale.set(.2);
    boy.x = 970;
    boy.y = 620;

    //返回
    const returnTextyre = PIXI.Texture.from("/pixi_res/scene02/game/返回按钮.png");
    const returnBtn = new PIXI.Sprite(returnTextyre);
    returnBtn.x = 20;
    returnBtn.y = 30
    returnBtn.interactive = true;
    returnBtn.on('click', return1);
  
    function return1() {
        emit('onReceiveGame', false)
    }




    //炸弹
    const bombList = [] as any;
    const bombTexture1 = PIXI.Texture.from("/pixi_res/scene02/game/刷单炸弹.png");
    const bombTexture2 = PIXI.Texture.from("/pixi_res/scene02/game/裸聊炸弹.png");
    const bombTexture3 = PIXI.Texture.from("/pixi_res/scene02/game/虚假贷款炸弹.png");
    const bombTexture0 = PIXI.Texture.from("/pixi_res/scene02/game/冒充客服炸弹.png");
    const bobms = [bombTexture0, bombTexture1, bombTexture2, bombTexture3];

    //爆炸效果

    const explodeTexture1 = PIXI.Texture.from("/pixi_res/scene02/game/boom1.png");
    const explodeTexture2 = PIXI.Texture.from("/pixi_res/scene02/game/boom2.png");
    const explodeTexture3 = PIXI.Texture.from("/pixi_res/scene02/game/boom3.png");
    const explodeTexture4 = PIXI.Texture.from("/pixi_res/scene02/game/boom4.png");
    const exolodes = [explodeTexture1, explodeTexture2, explodeTexture3, explodeTexture4];

    //金币
    var goldList = [] as any;
    var goldContainer = new PIXI.Container();
    const goldTexture = PIXI.Texture.from("/pixi_res/scene02/game/car.png");
    const gold1 = new PIXI.Sprite(goldTexture);
    const gold2 = new PIXI.Sprite(goldTexture);

    var speed = 0
    var isBegin = 0

    //游戏提示按钮
    const tipsBtnTexture = PIXI.Texture.from('/pixi_res/scene02/game/tipsBtn.png');
    const tipsBtn = new PIXI.Sprite(tipsBtnTexture);
    tipsBtn.x = 130;
    tipsBtn.y = 34;
    tipsBtn.scale.set(.63)

    //游戏提示
    const tipsTexture =  PIXI.Texture.from('/pixi_res/scene02/game/tips.png');
    const tips = new PIXI.Sprite(tipsTexture);
    tips.x = 450;
    tips.y = 150;

    tips.interactive = true;
    tips.on('click', closeTips);
    function closeTips() {
        app.stage.removeChild(tips);
    }
    tipsBtn.interactive = true;
    tipsBtn.on('click', openTips);
    function openTips() {
        app.stage.addChild(tips);
    }
    //开始按钮
    const startBtnTexture = PIXI.Texture.from("/pixi_res/scene02/game/start.png");
    const startBtn = new PIXI.Sprite(startBtnTexture);
    startBtn.anchor.set(.5, .5);
    startBtn.x = 980;
    startBtn.y = 470;

    startBtn.interactive = true;
    startBtn.on('click', startGame);
    function startGame() {
        speed = 6;
        app.stage.removeChild(startBtn);
        app.stage.addChild(pauseBtn);
        isBegin = 1;
    }

    app.stage.addChild(
        backgroundImage,
        boy,
        startBtn,
        returnBtn,
        tipsBtn,
        tips


    );

    //暂停按钮
    const pauseBtnTexture = PIXI.Texture.from("/pixi_res/scene02/game/pause.png");
    const pauseBtn = new PIXI.Sprite(pauseBtnTexture);
    pauseBtn.anchor.set(.5, .5);
    pauseBtn.x = 1150;
    pauseBtn.y = 60;

    pauseBtn.interactive = true;
    pauseBtn.on('click', pauseGame);
    function pauseGame() {
        speed = 0;
        app.stage.removeChild(pauseBtn);
        app.stage.addChild(continueBtn);
        isBegin = 0;

    }


    //继续按钮
    const continueBtnTexture = PIXI.Texture.from("/pixi_res/scene02/game/continue.png");
    const continueBtn = new PIXI.Sprite(continueBtnTexture);
    continueBtn.anchor.set(.5, .5);
    continueBtn.x = 980;
    continueBtn.y = 470;

    continueBtn.interactive = true;
    continueBtn.on('click', continueGame);
    function continueGame() {
        speed = 6;
        app.stage.removeChild(continueBtn);
        app.stage.addChild(pauseBtn);
        isBegin = 1;

    }
    //进度条
    var tiaoBgTexture = PIXI.Texture.from("/pixi_res/scene02/game/tiaoBg.png");
    const tiaoBg = new PIXI.Sprite(tiaoBgTexture)
    app.stage.addChild(tiaoBg);
    tiaoBg.x = 1200;
    tiaoBg.y = 40;

    var tiaoTexture = PIXI.Texture.from("/pixi_res/scene02/game/tiao.png");
    const tiao = new PIXI.Sprite(tiaoTexture)
    tiaoBg.addChild(tiao);
    tiao.x = 2;
    tiao.y = 2;

    //时钟
    var tiaoIconX = 380;
    var tiaoIconTexture = PIXI.Texture.from("/pixi_res/scene02/game/tiaoIcon.png");
    const tiaoIcon = new PIXI.Sprite(tiaoIconTexture);
    tiaoBg.addChild(tiaoIcon);
    tiaoIcon.anchor.x = 0.5;
    tiaoIcon.x = tiaoIconX;
    tiaoIcon.y = -30;

    var tiaoAllTime = 60 * 60;//固定 60秒 * 60帧
    var tiaoTime = 60 * 60;//当前

    //游戏结束
    var gameoverTexture = PIXI.Texture.from("/pixi_res/scene02/game/gameover.png");
    const gameover = new PIXI.Sprite(gameoverTexture);
    gameover.x = 980;
    gameover.y = 470;
    gameover.anchor.set(.5);
    app.stage.addChild(gameover);
    gameover.visible = false;


    //积分文字
    //文本样式
    var style = {
        font: '20px 微软雅黑',//加粗 倾斜 字号 字体名称
        fill: '#F7EDCA',//颜色
        stroke: '#4a1850',//描边颜色
        strokeThickness: 3,//描边宽度
        dropShadow: true,//开启阴影
        dropShadowColor: '#000000',//阴影颜色
        dropShadowAngle: Math.PI / 6,//阴影角度
        dropShadowDistance: 2,//投影距离
    };
    var credit = 0;
    var str = "当前获得积分：" + credit
    var timeTxt = new PIXI.Text(str, style);
    app.stage.addChild(timeTxt);
    timeTxt.x = 900;
    timeTxt.y = 30;

    app.ticker.add(animate);
    function animate() {
 

        if (isBegin == 1) {
            goldMove();
            bgMove();
            crash();
            addGold();
            addBomb();
            BombMove();
            if (tiaoTime < 0) {
                tiaoTime = 0;
                isBegin = 0;
                speed = 0;
                gameover.visible = true;

            }
            tiaoTime--;
            timeTxt.text = "当前获得积分：" + credit;

            tiao.scale.x = tiaoTime / tiaoAllTime;
            tiaoIcon.x = tiaoIconX * (tiaoTime / tiaoAllTime);
        }


    }
    //背景移动
    const bgMove = () => {
        if (backgroundImage.y >= 0) {
            backgroundImage.y = -1080
        }
        backgroundImage.y += speed;

    }


    //金币移动

    const goldMove = () => {
        for (let i = 0; i < goldList.length; i++) {
            goldList[i].y += speed;

        }
    }
    var a = 0;
    var b = 0;
    var c = 0;
    const addGold = () => {
        if (a == 66) {
            for (let i = 0; i < 3; i++) {
                const gold = new PIXI.Sprite(goldTexture);
                gold.x = 570;
                gold.y = i * 70 - 400;
                gold.anchor.set(.5, .5);
                gold.scale.set(1.3)
                app.stage.addChild(gold);
                goldList.push(gold)
            }
            a = 0
        }


        if (b == 110) {
            for (let i = 0; i < 5; i++) {
                const gold = new PIXI.Sprite(goldTexture);
                gold.x = 970;
                gold.y = i * 70 - 400;
                gold.anchor.set(.5, .5);
                gold.scale.set(1.3);
                app.stage.addChild(gold);
                goldList.push(gold)
            }
            b = 0
        }
        if (c == 250) {
            for (let i = 0; i < 7; i++) {
                const gold = new PIXI.Sprite(goldTexture);
                gold.x = 1370;
                gold.y = i * 70 - 400;
                gold.anchor.set(.5, .5);
                app.stage.addChild(gold);
                gold.scale.set(1.3);
                goldList.push(gold)
            }
            c = 0
        }
        a++;
        b++;
        c++;

    }
    var m = 0;
  
    const addBomb = () => {
        if (m == 170) {
            const num = Math.round(Math.random() * 3);
            
            const bomb = new PIXI.Sprite(bobms[num]);
            bomb.x = 570;
            bomb.y = 0;
            bomb.scale.set(.25);
            bomb.anchor.set(.5, .5);
            app.stage.addChild(bomb);
            bombList.push(bomb);
            m = 0
        }
        if (m == 50) {
            const num = Math.round(Math.random() * 3);

            const bomb = new PIXI.Sprite(bobms[num]);
            bomb.x = 970;
            bomb.y = 0;
            bomb.scale.set(.25);
            bomb.anchor.set(.5, .5);
            app.stage.addChild(bomb);
            bombList.push(bomb);
        }
        if (m == 100) {
            const num = Math.round(Math.random() * 3);

            const bomb = new PIXI.Sprite(bobms[num]);
            bomb.x = 1370;
            bomb.y = 0;
            bomb.scale.set(.25);
            bomb.anchor.set(.5, .5);
            app.stage.addChild(bomb);
            bombList.push(bomb);
        }
        m++

    }

    //炸弹移动
    const BombMove = () => {
        for (let i = 0; i < bombList.length; i++) {
            bombList[i].y += speed;

        }
    }
    //碰撞
    const crash = () => {
        //金币相碰
        for (let i = 0; i < goldList.length; i++) {
            var pos = (boy.x - goldList[i].x) * (boy.x - goldList[i].x) + (boy.y - goldList[i].y) * (boy.y - goldList[i].y);
            if (pos < 60 * 60) {
                app.stage.removeChild(goldList[i]);
                goldList.splice(i, 1);
                credit += 2;
            }
        }

        //碰到炸弹
        for (let i = 0; i < bombList.length; i++) {
            var pos = (boy.x - bombList[i].x) * (boy.x - bombList[i].x) + (boy.y - bombList[i].y) * (boy.y - bombList[i].y);
            if (pos < 60 * 60) {
                credit -= 10;
                app.stage.removeChild(bombList[i]);
                let num = Math.round(Math.random() * 3);
                const explode = new PIXI.Sprite(exolodes[num]);
                app.stage.addChild(explode);
                explode.scale.set(1.8);
                explode.x = 500;
                explode.y = 10;
                bombList.splice(i, 1);


                setTimeout(() => {
                    app.stage.removeChild(explode);
                }, 700)



            }
        }


    }


    document.addEventListener('keydown', function (e) {
        if (e.keyCode == 68 || e.keyCode == 39) {  //you

            if (boy.x < 1370) {
                boy.x += 400;
            } else {
                boy.x = 1370;
            }

        }

        if (e.keyCode == 65 || e.keyCode == 37) {  //zuo

            if (boy.x > 570) {
                boy.x -= 400;
            } else {
                boy.x = 570;
            }



        }
    })

   


})
</script>
<style scoped lang='scss'>
</style>
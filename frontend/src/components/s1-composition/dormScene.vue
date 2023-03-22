<template>
    <div v-show="Domup" class="Dormup">

        <img id="bg2" :src="dompath" alt="">
        <div class="return">
            <img class="returnBtn" src="../../img/导航栏/返回按钮.png" alt="" @click="re">
            <div class="returnTxt">
                返回
            </div>
        </div>
        <img v-show="arrow2" id="circle2" src="../../img/scene01/circle.png" alt="" @click="inPc">
        <img v-show="arrow2" id="arrow2" src="../../img/scene01/提示标.png" @click="inPc" alt="">

        <div class="page1" v-show="page1">
            <img id="purchaseImg" :src="pcpath" alt="">
            <img v-show="butn1" id="next1" src="../../img/scene01/dom/购买按钮.png" alt="" @click="next1">
            <img v-show="butn2" id="next2" src="../../img/scene01/dom/付款按钮.png" alt="" @click="next2">
            <img v-show="msg1" @click="msg" class="msg" src="../../img/scene01/dom/消息.png" alt="">

            <div v-show="chat" class="chat">

                <div class="chatTop">
                    <img class="icon icon1" src="../../img/scene01/dom/icon.jpeg" alt="">
                    <h4>云客服助手</h4>
                </div>
                <div class="chatSide"></div>
                <div class="chatBody">
                    <div class="chat1">
                        <img class="icon icon2" src="../../img/scene01/dom/卖家头像.png" alt="">

                        <div class="right1">
                            <div class="line1">
                                <span class="left">云客服助手</span>
                                <span class="right">16:24:38</span>
                            </div>
                            <div class="line2">
                                亲，这边显示您今天购买了我们店的“学生窗帘”这一物品，现在有优惠，可以帮您免单，您看需要吗
                            </div>
                        </div>
                    </div>
                    <div v-show="chat2" class="chat2">

                        <img class="icon icon3" src="../../img/scene01/dom/买家头像.png" alt="">
                        <div class="left1">
                            <div class="line1">
                                <span class="left">快乐星球</span>
                                <span class="right">16:24:38</span>

                            </div>
                            <div class="line2">
                                怎么免单呢？
                            </div>
                        </div>


                    </div>
                    <div v-show="chat3" class="chat3">

                        <img class="icon icon1" src="../../img/scene01/dom/卖家头像.png" alt="">
                        <div class="right1">
                            <div class="line1">
                                <span class="left">云客服助手</span>
                                <span class="right">16:24:38</span>

                            </div>
                            <div class="line2">
                                已经由淘宝将原款198元以红包的形式发您了，您看一下呢
                            </div>
                        </div>


                    </div>
                    <div v-show="chat4" class="chat4">

                        <img class="icon icon1" src="../../img/scene01/dom/卖家头像.png" alt="">
                        <div class="right1">
                            <div class="line1">
                                <span class="left">云客服助手</span>
                                <span class="right">16:24:38</span>

                            </div>
                            <div class="line2">
                                <img @click="openbag" class="redbag" :src="redbagPath" alt="">
                            </div>
                            <h5 v-show="h5">
                                您领取了 云客服助手 的<span style="color: red;">红包</span>
                            </h5>
                        </div>
                        <div v-show="redbag1">
                            <img class="redbag1" src="../../img/scene01/dom/打开的红包.png" alt="">
                            <img @click="closebag" class="closebag" src="../../img/scene01/dom/红包收下按钮.png" alt="">
                        </div>
                    </div>
                    <div v-show="chat5" class="chat5">

                        <img class="icon icon3" src="../../img/scene01/dom/买家头像.png" alt="">
                        <div class="left1">
                            <div class="line1">
                                <span class="left">快乐星球</span>
                                <span class="right">16:24:38</span>

                            </div>
                            <div class="line2">
                                确实收到了，为什么呢？
                            </div>
                        </div>


                    </div>
                    <div v-show="chat6" class="chat6">

                        <img class="icon icon1" src="../../img/scene01/dom/卖家头像.png" alt="">
                        <div class="right1">
                            <div class="line1">
                                <span class="left">云客服助手</span>
                                <span class="right">16:24:38</span>

                            </div>
                            <div class="line2">
                                这是我们店本月的优惠，因为好评较多，我们每个月都会有这样的优惠名额的。您可以平时只需要帮忙刷一些好评即可获得大额返现。您看到我们已经返还了您将近200的金额，所以肯定是正规商家哈，不用顾虑。
                            </div>
                        </div>



                    </div>
                    <div v-show="chat7" class="chat7">

                        <img class="icon icon1" src="../../img/scene01/dom/卖家头像.png" alt="">
                        <div class="right1">
                            <div class="line1">
                                <span class="left">云客服助手</span>
                                <span class="right">16:24:38</span>

                            </div>
                            <div class="line2">
                                亲可以点击链接 <span @click="openA"
                                    style="cursor:pointer;color: blue;text-decoration:underline">************</span>试一下呢，每单都是稳赚的，特别适合像亲亲这样的大学生，月入三千不是梦哈~
                            </div>

                            <div v-show="rmb" class="rmb"> + ￥20</div>
                        </div>



                    </div>
                    <div v-show="chat8" class="chat8">

                        <img class="icon icon3" src="../../img/scene01/dom/买家头像.png" alt="">
                        <div class="left1">
                            <div class="line1">
                                <span class="left">快乐星球</span>
                                <span class="right">16:24:38</span>

                            </div>
                            <div class="line2">
                                刷了一单，的确返现了20元
                            </div>
                        </div>


                    </div>
                    <div v-show="chat9" class="chat6">

                        <img class="icon icon1" src="../../img/scene01/dom/买家头像.png" alt="">
                        <div class="right1">
                            <div class="line1">
                                <span class="left">快乐星球</span>
                                <span class="right">16:24:38</span>

                            </div>
                            <div class="line2">
                                平时每天赚500元以上是没有问题的呢，只是因为是内部活动，每个人需要缴纳300元的保证金，我们将在您提现300元后将这个钱全数返回。可以看提现成功的截图哦。
                            </div>
                        </div>



                    </div>
                </div>
                <div class="chatInput">
                    <div v-show="Btn1" class="Btn1">
                        <div class="chose1" @click="chose1">怎么免单呢</div>
                        <div class="chose2" @click="close1">不用了哦</div>
                    </div>
                    <!-- <div v-show="Btn2" class="Btn1">
                        <div class="chose1" @click="chose1">怎么免单呢</div>
                        <div class="chose2">不用了哦</div> -->
                    <!-- </div> -->
                    <div v-show="Btn3" class="Btn1">
                        <div class="chose1" @click="chose3">缴纳保证金</div>
                        <div class="chose2" @click="exict4">关闭窗口</div>
                    </div>
                </div>


            </div>
        </div>

    </div>
</template>
<script setup lang='ts'>
import { onMounted, ref, nextTick,reactive } from 'vue';
import { gsap } from 'gsap'
import { ElMessage, valueEquals } from 'element-plus';

import { setPersonaData } from '../../hooks';


const mytimeIn1 = ref(0);
const mytimeOut1 = ref(0);

type FraudData = {
    fraudResult: boolean;
    fraudType: string;
    hesitateTime: number;
}
const form = reactive<FraudData>({
    fraudResult: true,
    fraudType: "刷单诈骗",
    hesitateTime: 0,     /*犹豫时间 单位毫秒*/
});

const arrow2 = ref(true);  //对话框显示
const page1 = ref(false);  //对话框显示
const butn1 = ref(true); //加入购物车
const butn2 = ref(false); //提交订单
const msg1 = ref(false);
const redbag1 = ref(false);
const h5 = ref(false);

const Btn1 = ref(true);
const Btn2 = ref(false);
const Btn3 = ref(false);
const Btn4 = ref(false);

const chat = ref(false);
const chat2 = ref(false);
const chat3 = ref(false);
const chat4 = ref(false);
const chat5 = ref(false);
const chat6 = ref(false);
const chat7 = ref(false);
const chat8 = ref(false);
const chat9 = ref(false);
const rmb = ref(false);

const Domup = ref(true);

const dompath = ref("/pixi_res/scene01/场景3宿舍内.png");
const pcpath = ref("/pixi_res/scene01/dom/购买.png");

const redbagPath = ref("/pixi_res/scene01/dom/红包未领.png")
const inPc = () => {
    dompath.value = "/pixi_res/scene01/电脑.png";
    arrow2.value = false;
    page1.value = true;
}

const next1 = () => {
    pcpath.value = "/pixi_res/scene01/dom/付款.png"
    butn1.value = false;
    butn2.value = true;
}
const next2 = () => {
    pcpath.value = "/pixi_res/scene01/dom/付款.png"  //支付成功界面
    butn1.value = false;
    butn2.value = false;
    msg1.value = true;

    let datalist = sessionStorage.getItem("money");
    datalist = JSON.stringify((JSON.parse(datalist || '') -0) -(117.9-0));
    sessionStorage.setItem("money", datalist);
    emit("onReceiveMsgDormMoney", datalist)

}

const msg = () => {
    chat.value = true;
    msg1.value = false;
}

const chose1 = () => {

    Btn1.value = false;
    chat2.value = true;

    setTimeout(() => {
        chat3.value = true;

    }, 1500)
    setTimeout(() => {
        chat4.value = true;

        scrollToBottom();

    }, 2200)


}

const emit = defineEmits(["onReceiveMsgDom","onReceiveMsgDormMoney"]);
const chose3 = () => {
    mytimeOut1.value = +new Date;
    form.hesitateTime = mytimeOut1.value - mytimeIn1.value;
    form.fraudResult = true;
    const b = JSON.parse(JSON.stringify(form));
    setPersonaData({data:b})
        .then(res => {
        console.log(b)
        }).catch(() => ElMessage("网络异常"))
    
    chat.value = false;
   
    let datalist = sessionStorage.getItem("money");
    datalist = JSON.stringify((JSON.parse(datalist || '') - 0) - (300 - 0));
    sessionStorage.setItem("money", datalist);
    emit("onReceiveMsgDormMoney", datalist);
 



}
const exict4 = () => {
    mytimeOut1.value = +new Date;
    form.hesitateTime= mytimeOut1.value - mytimeIn1.value;
    form.fraudResult = false;

    const b = JSON.parse(JSON.stringify(form));
    setPersonaData({data:b})
        .then(res => {
            console.log(res)
        }).catch(() => ElMessage("网络异常"))
    chat.value = false;
   
}

const openbag = () => {
    redbag1.value = true;

    let datalist = sessionStorage.getItem("money");
    datalist = JSON.stringify((JSON.parse(datalist || '') - 0) + (117.9 - 0));
    sessionStorage.setItem("money", datalist);
    emit("onReceiveMsgDormMoney", datalist)
}
const closebag = () => {
    redbag1.value = false;
    h5.value = true;
    chat5.value = true;
    redbagPath.value = "/pixi_res/scene01/dom/红包已领.png"
    scrollToBottom();
    setTimeout(() => {
        chat6.value = true;

        scrollToBottom();
    }, 2000)
    setTimeout(() => {

        chat7.value = true;
        scrollToBottom();
    }, 2400)
}

const scrollToBottom = () => {
    nextTick(() => {
        var div = document.getElementsByClassName('chatBody')[0];
        div.scrollTop = div.scrollHeight;

    })
}
const openA = () => {
    rmb.value = true;
    const rmb1 = gsap.timeline();
    rmb1.fromTo(".rmb", 2, { scale: .5, opacity: .2 }, { scale: 1.5, opacity: 1 })
        .to(".rmb", 1.5, { scale: 0, opacity: .5 })

    let datalist = sessionStorage.getItem("money");
    datalist = JSON.stringify(JSON.parse(datalist || '') + 20);
    sessionStorage.setItem("money", datalist);
    emit("onReceiveMsgDormMoney",datalist)
    setTimeout(() => {
        chat8.value = true;
        scrollToBottom();
    }, 2600)

    setTimeout(() => {
        chat9.value = true;
        scrollToBottom();
    }, 3300)
    setTimeout(() => {
        Btn3.value = true;
        mytimeIn1.value = +new Date;
        scrollToBottom();
    }, 3900)
}
const re = () => {
    Domup.value = false;
    emit("onReceiveMsgDom", Domup.value);
    sessionStorage.setItem("fraud3", JSON.stringify(form));
    console.log(sessionStorage);
}
const close1 = () => {
    
}
onMounted(() => {

    const fangda2 = gsap.timeline();
    fangda2.to("#circle2", 1.2, { scale: 1.6 })
        .to("#circle2", 1.2, { scale: 1 })
        .repeat(300)
    const fangda3 = gsap.timeline();
    fangda3.to("#arrow2", 1.2, { top: 57+'%' })
        .to("#arrow2", 1.2, { top: 55+'%' })
        .repeat(300)
        



    //    




    //     // scrollToBottom();

})
</script>
<style scoped lang='scss'>
.page1 {
    position: absolute;
    width: 58%;
    height: 59%;
    left: 51%;
    transform: translate(-50%);
    top: 18%;
    // background-color: aqua;
    overflow: scroll;

    #purchaseImg {
        width: 100%;

        // height: 100%;

    }

    #next1,
    #next2 {
        position: absolute;
        left: 45.5%;
        width: 20%;
        top: 90%;
        cursor: pointer;
    }

    #next2 {
        top: 95%;
        left: 69%;
    }

    .chat {
        position: absolute;
        width: 76%;
        height: 87%;
        background-color: rgb(255, 255, 255);
        top: 12%;
        left: 13%;
        border: 1px solid rgb(70, 129, 208);

        .chatTop {
            width: 100%;
            height: 10%;
            background-color: rgb(70, 129, 208);
            line-height: 10%;
        }

        .chatBody {
            width: 77%;
            height: 70%;
            background-color: rgb(240, 240, 240);
            overflow: auto;



            .chat1 {
                width: 100%;
                height: 33%;
                // background-color: rgb(223, 95, 95);

                .right1 {
                    float: left;
                    margin-top: 1.5%;
                    // background-color: #fff;
                    width: 80%;
                    text-align: left;
                    margin-left: 2%;

                    .line1 {}

                    .left {
                        font-size: .7rem;
                        font-weight: bold;

                    }

                    .right {
                        font-size: .7rem;
                        margin-left: 2%;
                        color: rgb(137, 137, 137);
                    }

                    .line2 {
                        width: 93%;
                        // height: 60%;
                        word-wrap: break-word;
                        word-break: break-all;
                        background-color: #fff;
                        padding: 10px;
                        border-radius: 0 13px 13px 13px;

                        margin-top: 2%;

                    }
                }

            }

            .chat2 {
                width: 100%;
                height: 22%;
                // background-color: rgb(152, 89, 89);

                .left1 {
                    float: right;
                    // margin-top: 1.5%;
                    // background-color: rgb(121, 32, 32);
                    width: 80%;
                    text-align: right;
                    margin-right: 2%;

                    .left {
                        font-size: .7rem;
                        font-weight: bold;

                    }

                    .right {
                        font-size: .7rem;
                        margin-left: 2%;
                        color: rgb(137, 137, 137);
                    }

                    .line2 {
                        float: right;
                        width: 22%;
                        // height: 60%;
                        word-wrap: break-word;
                        word-break: break-all;
                        background-color: rgb(192, 226, 251);
                        padding: 10px;
                        border-radius: 13px 0 13px 13px;

                        margin-top: 1%;
                        // margin-left: 5.7%;

                    }
                }

            }

            .chat3 {
                width: 100%;
                height: 27.8%;
                // background-color: rgb(223, 95, 95);

                .right1 {
                    float: left;
                    // margin-top: 1.5%;
                    // background-color: #fff;
                    width: 80%;
                    text-align: left;
                    margin-left: 2%;

                    .line1 {
                        // background-color: #fff;
                    }

                    .left {
                        font-size: .7rem;
                        font-weight: bold;

                    }

                    .right {
                        font-size: .7rem;
                        margin-left: 2%;
                        color: rgb(137, 137, 137);
                    }

                    .line2 {
                        width: 88%;
                        // height: 60%;
                        word-wrap: break-word;
                        word-break: break-all;
                        background-color: #fff;
                        padding: 10px;
                        border-radius: 0 13px 13px 13px;

                        margin-top: 2%;

                    }
                }

            }

            .chat4 {
                width: 100%;
                height: 47.5%;
                // background-color: rgb(223, 95, 95);

                .right1 {
                    float: left;
                    // margin-top: 1.5%;
                    // background-color: #fff;
                    width: 80%;
                    text-align: left;
                    margin-left: 2%;

                    .line1 {
                        // background-color: #fff;
                    }

                    .left {
                        font-size: .7rem;
                        font-weight: bold;

                    }

                    .right {
                        font-size: .7rem;
                        margin-left: 2%;
                        color: rgb(137, 137, 137);
                    }

                    .line2 {
                        // width: 66%;
                        // // height: 60%;
                        // word-wrap: break-word;
                        // word-break: break-all;
                        // background-color: #fff;
                        // padding: 10px;
                        // border-radius: 0 13px 13px 13px;

                        margin-top: 2%;

                    }
                }

            }

            .chat5 {
                width: 100%;
                height: 22%;
                // background-color: rgb(152, 89, 89);

                .left1 {
                    float: right;
                    // margin-top: 1.5%;
                    // background-color: rgb(121, 32, 32);
                    width: 80%;
                    text-align: right;
                    margin-right: 2%;

                    .left {
                        font-size: .7rem;
                        font-weight: bold;

                    }

                    .right {
                        font-size: .7rem;
                        margin-left: 2%;
                        color: rgb(137, 137, 137);
                    }

                    .line2 {
                        float: right;
                        width: 38%;
                        // height: 60%;
                        word-wrap: break-word;
                        word-break: break-all;
                        background-color: rgb(192, 226, 251);
                        padding: 10px;
                        border-radius: 13px 0 13px 13px;

                        margin-top: 1%;
                        // margin-left: 5.7%;

                    }
                }

            }

            .chat6 {
                width: 100%;
                height: 38%;
                // background-color: rgb(223, 95, 95);

                .right1 {
                    float: left;
                    // margin-top: 1.5%;
                    // background-color: #fff;
                    width: 80%;
                    text-align: left;
                    margin-left: 2%;

                    .line1 {
                        // background-color: #fff;
                    }

                    .left {
                        font-size: .7rem;
                        font-weight: bold;

                    }

                    .right {
                        font-size: .7rem;
                        margin-left: 2%;
                        color: rgb(137, 137, 137);
                    }

                    .line2 {
                        // width: 80%;
                        // height: 60%;
                        word-wrap: break-word;
                        word-break: break-all;
                        background-color: #fff;
                        padding: 10px;
                        border-radius: 0 13px 13px 13px;

                        margin-top: 2%;

                    }
                }

            }

            .chat7 {
                width: 100%;
                height: 30%;
                // background-color: rgb(223, 95, 95);

                .right1 {
                    float: left;
                    // margin-top: 1.5%;
                    // background-color: #fff;
                    width: 80%;
                    text-align: left;
                    margin-left: 2%;

                    .line1 {
                        // background-color: #fff;
                    }

                    .left {
                        font-size: .7rem;
                        font-weight: bold;

                    }

                    .right {
                        font-size: .7rem;
                        margin-left: 2%;
                        color: rgb(137, 137, 137);
                    }

                    .line2 {
                        // width: 80%;
                        // height: 60%;
                        word-wrap: break-word;
                        word-break: break-all;
                        background-color: #fff;
                        padding: 10px;
                        border-radius: 0 13px 13px 13px;

                        margin-top: 2%;

                    }
                }

                .rmb {
                    font-size: 2rem;
                    position: absolute;
                    top: 60%;
                    left: 30%;
                    font-weight: bolder;
                }

            }

            .chat8 {
                width: 100%;
                height: 22%;
                // background-color: rgb(152, 89, 89);

                .left1 {
                    float: right;
                    // margin-top: 1.5%;
                    // background-color: rgb(121, 32, 32);
                    width: 80%;
                    text-align: right;
                    margin-right: 2%;

                    .left {
                        font-size: .7rem;
                        font-weight: bold;

                    }

                    .right {
                        font-size: .7rem;
                        margin-left: 2%;
                        color: rgb(137, 137, 137);
                    }

                    .line2 {
                        float: right;
                        width: 42%;
                        // height: 60%;
                        word-wrap: break-word;
                        word-break: break-all;
                        background-color: rgb(192, 226, 251);
                        padding: 10px;
                        border-radius: 13px 0 13px 13px;

                        margin-top: 1%;
                        // margin-left: 5.7%;

                    }
                }

            }

            .icon {
                width: 7%;
            }

            .icon2 {
                margin-top: 2%;
                width: 6%;
                float: left;
                margin-left: 2%;

            }

            .icon3 {
                float: right;
                // background-color: rgb(144, 77, 77);
                margin-right: 2%;
                // margin-top: 16%;
                width: 6%;
            }

            .redbag {
                width: 40%;
                cursor: pointer;
            }

            .redbag1,
            .closebag {
                width: 30%;
                position: absolute;
                top: 15%;
                left: 25%;
            }

            .closebag {

                top: 83%;
                width: 22%;
                left: 29%;

                cursor: pointer;

            }
        }

        .chatSide {
            float: right;
            width: 22.8%;
            height: 90%;
            background-color: rgb(161, 139, 139);
            background: url('../../img/scene01/dom/侧边栏.png') no-repeat;
            background-size: 100% 100%;
            border-left: 1px solid rgb(70, 129, 208);
        }

        .chatInput {
            .Btn1 {
                display: flex;
                justify-content: space-around;
                flex-wrap: wrap;

            }

            .Btn1 div {
                display: inline-block;
                background-color: rgb(224, 224, 224);
                width: 30%;
                height: 30px;
                line-height: 30px;
                border-radius: 8px;
                margin-top: 5%;
                cursor: pointer;
                color: rgb(43, 148, 223);
                border: 1px solid rgb(130, 130, 130);
            }
        }


        .icon {
            width: 5%;



            // height: ;
        }

        .icon1 {
            margin-top: .5%;
            float: left;
            margin-left: 2%;
        }







        h4 {
            float: left;
            font-size: .8rem;
            margin-top: 1.5%;
            margin-left: 1.2%;
        }

        h5 {
            margin: 0 auto;
            margin-top: 2%;
            background-color: rgba(164, 162, 162, 0.393);
            width: 35%;
            height: 24px;
            line-height: 24px;
            border-radius: 5px;
            text-align: center;
            color: #fff;
            font-weight: 400;

        }

        .line2 {
            font-size: 1rem;
        }

    }
}

.msg {
    position: absolute;
    width: 18%;
    position: fixed;
    bottom: 10px;
    left: 5%;
    cursor: pointer;
}
.Dormup {
    #bg2 {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0;

    }

    #arrow2 {
        position: absolute;
        top: 55%;
        left: 66%;
        width: 4%;
        cursor: pointer;
    }

    #circle2 {
        position: absolute;
        top: 64%;
        left: 65.5%;
        width: 5%;
        cursor: pointer;
    }
}
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
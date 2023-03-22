<template>
    <div class="bgImg">
        <Navabar :data="datalist" />
        <div v-show="showMsg" class="message" style="z-index: 100;">

            <img v-show="msg" @click="answer" class="msg" src="../../img/scene01/phone/来电提示.png" alt="">

            <div class="phone">
                <img class="phone1" :src="pathPhone" alt="">
                <div class="Btn">
                    <input v-show="Btn1" type="button" value="是的，我乘坐过" @click="choose1">

                    <!--//关键选项 -->
                    <!-- <div v-show="Btn2" class="Btn4"> -->
                    <input v-show="Btn2" type="button" value="以防疫为重，我戴好口罩原地等待吧" @click="choose2">
                    <input v-show="Btn2" type="button" value="这样会赶不及报道的，我还是先挂了" @click="close">
                    <!-- </div> -->
                </div>
            </div>
            <img id="pop" src="../../img/scene01/通话框.png" alt="" style="z-index: 100;">
            <img v-show="note" class="note" src="../../img/scene01/phone/新短信.png" alt="" @click="changeNote">
            <audio id="audio1" :src="audioSrc" ref="Audio1"></audio>
            <audio id="audio2" :src="audioSrc" ref="Audio2"></audio>
            <audio id="audio3" :src="audioSrc" ref="Audio3"></audio>
            <div class="text" style="z-index: 100;">

                <div class="text1" v-show="text1" v-for="(item) in items">{{ item.message1 }}</div>
                <div class="text1" v-show="text2" v-for="(item) in items">{{ item.message2 }}</div>
                <div class="text1" v-show="text3" v-for="(item) in items">{{ item.message3 }}</div>
                <div class="text1" v-show="text4" v-for="(item) in items">{{ item.message4 }}</div>
                <div class="text1" v-show="text5" v-for="(item) in items">{{ item.message5 }}</div>
                <div class="text1" v-show="text6" v-for="(item) in items">{{ item.message6 }}</div>
                <div class="text1" v-show="text7" v-for="(item) in items">{{ item.message7 }}</div>
                <div class="text1" v-show="text8" v-for="(item) in items">{{ item.message8 }}</div>
                <div class="text1" v-show="text9" v-for="(item) in items">{{ item.message9 }}</div>
            </div>


            <div class="notetext" v-show="notetext">
                <div class="n1 nn" v-for="note in noteItems">{{ note.note1 }}</div>
                <div class="b1 bb" v-for="btn in btns" v-show="b1">{{ btn.btn1 }}</div>
                <div class="n2 nn" v-for="note in noteItems" v-show="n1">{{ note.note2 }}</div>
            </div>
            <div class="noteBtn">
                <div class="noteBtn11 noteBtn1" v-show="noteBtn1">
                    <input type="button" value="完善信息" @click="choose3">
                    <input type="button" value="不予理睬" @click="close">
                </div>
                <div class="noteBtn11 noteBtn2" v-show="noteBtn2">
                    <input type="button" value="支付￥1400，以免影响入学" @click="close">
                    <input type="button" value="不少一笔钱，打电话确认吧" @click="close">
                </div>
            </div>

        </div>
        <div v-show="end" class="end">
            <img id="map" src="../../img/scene01/map.png" alt="">
        </div>

    </div>



</template>
<script setup lang='ts'>




import { onMounted, ref, reactive, computed } from 'vue'
import { gsap } from 'gsap'


import { useRouter } from 'vue-router';
import { setPersonaData } from '../../hooks/use-http'
import { ElMessage } from 'element-plus';
import Navabar from '../s1-composition/Navabar.vue';

import phoneImg0 from '../../img/scene01/phone/来电手机（没有通话时间.png';
import phoneImg1 from '../../img/scene01/phone/通话结束.png';
import phoneImg2 from '../../img/scene01/phone/短信.png';

const router = useRouter();


sessionStorage.setItem("money", "6000");

type dataType = {
    scenename: string,
    money: string
};

const datalist = reactive<dataType>({
    scenename: "校门口",
    money: "6000"
})



const mytimeIn1 = ref(0);
const mytimeOut1 = ref(0);



const showMsg = ref(true);
const msg = ref(true);
const text1 = ref(false);
const text2 = ref(false);
const text3 = ref(false);
const text4 = ref(false);
const text5 = ref(false);
const text6 = ref(false);
const text7 = ref(false);
const text8 = ref(false);
const text9 = ref(false);

const Btn1 = ref(false);
const Btn2 = ref(false);
const note = ref(true);

const b1 = ref(false);
const n1 = ref(false);
const notetext = ref(false);
const noteBtn2 = ref(false);
const noteBtn1 = ref(false);

const Audio1 = ref<HTMLAudioElement>();
const Audio2 = ref<HTMLAudioElement>();
const Audio3 = ref<HTMLAudioElement>();

const audioSrc = `/audio/1.mp3?${Math.floor(Math.random()*10000)}`;

const end = ref(false);

const pathPhone = ref<string>(phoneImg0);

type FraudData = {
    fraudResult: boolean;
    fraudType: string;
    hesitateTime: number;
}
const form = reactive<FraudData>({
    fraudResult: true,
    fraudType: "新型诈骗",
    hesitateTime: 0,     /*犹豫时间 单位毫秒*/
});

const items = [
    {
        message1: " 您好，我们这边是疫情防控中心",
        id: 1,

    },
    {
        message2: " 我们这边根据行程大数据监测到您于****（时间）乘坐过*****（航班号）这趟航班是吗？",
        id: 2,

    },
    {
        message3: " 我们这边监测到，*先生，您乘坐的航班上有一名新冠疫情阳性患者的密切接触者",

    },
    {
        message4: " 所以您现在是次密接人员，健康码将会在两小时内变黄，我们的医护人员接您去指定地点隔离",

    },
    {
        message5: " 您可以原地等待吗？",

    },
    {
        message6: " 好的，我们的医护车将于半小时内到达您的地点，请您原地等待",

    },
    {
        message7: " 好的，我们的医护车将于半小时内到达您的地点，请您原地等待",

    },
    {
        message8: "稍后我们专门负责转接隔离人员的公安机关将会给您发送一条短信",

    },
    {
        message9: "请您按照短信步骤完善个人信息，再见！",

    },
];
const noteItems = [
    {
        note1: "您好，我们是**区疫情防控中心的，您的信息尚未完善，请你进一步完善信息，网址：********* ",
        note2: "好的，隔离地点在***，隔离酒店所产生的酒店是不需要您本人承担的"
            + "但是酒店住宿产生的押金需要您预先支付一下，押金会在结束隔离后的一个工作日内返还。" +
            "如果半小时内无法成功办理隔离入住，您的健康码将变黄，影响您入学，届时产生的隔离费用将由您个人承担。支付的网页：*******"
    },


]

const btns = [
    {
        btn1: "我填好了。"
    }
]

const aa = () => {
    alert("999");
}



// const audio11 = ref<HTMLAudioElement>(document.getElementById("audio1"));

const answer = () => {


    msg.value = false;

    const phoneShow = gsap.timeline();
    phoneShow.fromTo('.phone1', 1.2, { width: 0, opacity: 0 }, { width: 100 + '%', opacity: 1 });

    setTimeout(function () {

        Audio1.value?.play();
        text1.value = true;
        const pop2 = gsap.timeline();
        pop2.to("#pop", .2, { width: 650 })
        setTimeout(function () {
            text1.value = false;
            text2.value = true;
            const pop2 = gsap.timeline();
            pop2.to("#pop", .2, { width: 1450 })
        }, 3000);

    }, 1000)

    Audio1.value?.addEventListener('ended', function () {
        // alert('播放完毕');
        text2.value = false;
        Btn1.value = true;
        const pop2 = gsap.timeline();
        pop2.to("#pop", .1, { width: 0 })
    }, false)

}
const choose1 = () => {

    Btn1.value = false;
    Audio2.value?.play();
    text3.value = true;
    const pop2 = gsap.timeline();
    pop2.to("#pop", .2, { width: 1450 })
    setTimeout(function () {
        text3.value = false;
        text4.value = true;
        const pop2 = gsap.timeline();
        pop2.to("#pop", .2, { width: 1550 })
    }, 2000)
    setTimeout(function () {
        text4.value = false;
        text5.value = true;
        const pop2 = gsap.timeline();
        pop2.to("#pop", .2, { width: 450 })
    }, 5000)

    Audio2.value?.addEventListener('ended', function () {
        Btn2.value = true;
        text5.value = false;
        const pop2 = gsap.timeline();
        pop2.to("#pop", .1, { width: 0 });
         mytimeIn1.value = +new Date;
       

    })
}


const choose2 = () => {   //被骗
    mytimeOut1.value = +new Date;
    form.hesitateTime= mytimeOut1.value - mytimeIn1.value;
    console.log("犹豫时间为：" + form.hesitateTime);
   form.fraudResult = true;

    // let a: any = {
    //     "fraudType":fraudType.value,
    //     "hesitateTime":hesitateTime.value,
    //     "fraudResult":fraudResult.value
    // }
    const b = JSON.parse(JSON.stringify(form));

    setPersonaData({ data: b })
        .then(res => {
            console.log(res.data);
            console.log(b);
    }).catch(() => ElMessage("网络异常，请稍后再试"))
  
    
    Btn2.value = false;
    Audio3.value?.play();
    text6.value = true;
    const pop2 = gsap.timeline();
    pop2.to("#pop", .2, { width: 1350 })
    setTimeout(function () {
        text6.value = false;
        text7.value = true;
        const pop2 = gsap.timeline();
        pop2.to("#pop", .2, { width: 1350 })
    }, 1500);
    setTimeout(function () {
        text7.value = false;
        text8.value = true;

    }, 4400)
    setTimeout(function () {
        text8.value = false;
        text9.value = true;
        const pop2 = gsap.timeline();
        pop2.to("#pop", .2, { width: 850 })
    }, 6000)
    Audio3.value?.addEventListener('ended', function () {
       
        // Btn2.value = true;
        text9.value = false;
        const pop2 = gsap.timeline();
        pop2.to("#pop", .1, { width: 0 })
        pathPhone.value = phoneImg1
        setTimeout(function () {
            const note1 = gsap.timeline();
            note1.fromTo('.note', .8, { height: 0, width: 19+ '%' }, { height: 9 + '%', width: 19 + '%' })

        }, 1400)
    })

}
const close = () => {
    text2.value = false;
    Btn2.value = false;
    showMsg.value = false
    router.push('/school');
    // end.value = true;

    datalist.money = JSON.stringify(JSON.parse(sessionStorage.getItem("money") || '') - 1400);
    sessionStorage.setItem("money", datalist.money);
    sessionStorage.setItem("fraud1", JSON.stringify(form));
   
   
}

const changeNote = () => {
    pathPhone.value = phoneImg2;
    note.value = false;

    notetext.value = true;

    setTimeout(function () {
        noteBtn1.value = true;
    }, 1500)

}

const choose3 = () => {
    noteBtn1.value = false;
    b1.value = true;
    setTimeout(function () {
        n1.value = true;
    }, 1400)
    setTimeout(function () {
        noteBtn2.value = true;
    }, 2400)

    

}

onMounted(() => {
  
  

    setInterval(function () {
        const msgShake = gsap.timeline();
        msgShake.fromTo('.msg', .1, { translateY: 2, rotateZ: 3 }, { translateY: -2, rotateZ: -3 })
            .to('.msg', .1, { translateY: 0, rotateZ: 0 })
            .repeat(4)

    }, 1500)


}
)








</script>
<style scoped lang='scss'>


.bgImg {

    position: absolute;
    width: 100;
    height: 100%;
    background: url('../../img/scene01/校门口.png') no-repeat;
    background-size: 100% 100%;
    // z-index: -100;
}

.msg {
    position: absolute;
    width: 18%;
    position: fixed;
    bottom: 10px;
    left: 5%;
    cursor: pointer;
    // z-index: -1;
    
}



#pop {
    position: absolute;
    width: 0;
    height: 10%;
    transform: translate(-50%);
    top: 16.5%;
    z-index: -10;
}

.note {
    // width: 20%;
    height: 0;
    position: absolute;
    top: 35.9%;
    transform: translate(-50%);
    cursor: pointer;
}
.phone{
    // background-color: rgb(141, 69, 69);
    position: relative;
    width:25.6vw;
    height: 74vh;
    left: 50%;
    transform: translate(-50%);
   position: fixed;
   bottom: 0;

    .phone1 {

            position: fixed;

            width: 100%;
            opacity: 0;
            transform: translate(-50%);
            bottom: -2%;
        }
     
                .Btn {
                    position: absolute;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    top: 75%;
                    cursor: pointer;
                    height: 18%;
                    width: 70%;
                    // background-color: rgb(189, 115, 115);
                            
                    input {

                            width: 100%;
                            height: 50%;
                            text-align: center;
                            padding: 0 10px;
                            border-radius: 15px;
                            display: block;
                            margin-top: 26px;
                            cursor: pointer;
                            border: 1px solid rgb(107, 100, 106);

                            font-size: 18px;
                        }
        
                  
                 
                }
                                 
}

//字幕
.text {

    font: 36px bold;
    position: fixed;
    // background-color: #fff;
    width: 99%;
    top: 18%;
    text-align: center;
    z-index: -5;
}

input {

    text-align: center;
    padding: 0 10px;
    border-radius: 10px;
    display: block;
    margin-top: 20px;
    cursor: pointer;

    // border-style: outset;
    font-size: 19px;
}



.notetext {
    position: absolute;
    top: 66%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 18%;
    // background-color: rgb(124, 84, 84);
    text-align: left;
    // display: flex;
    height: 450px;
    // justify-content: flex-end;



    .nn {

        background-color: #fff;
        padding: 12px;
        padding-left: 17px;
        border-radius: 0 13px 13px 13px;
        font-size: 18px;
        float: left;

    }

    .bb {
        // width: 330px;
        background-color: rgb(206, 230, 201);
        padding: 12px;
        border-radius: 13px 0 13px 13px;
        font-size: 18px;
        margin-top: 20px;

        float: right;


    }

    .b1 {
        margin-top: 20px;
        width: 100px;
        height: 25px;

    }

    .n1 {

        padding: 10px;
        border-radius: 0 13px 13px 13px;
        font-size: 17px;
        // margin-top: 30px;
        height: 60px;
        padding-left: 17px;


    }

    .n2 {
        margin-top: 20px;
        height: 200px;
    }



}

.noteBtn {
    position: absolute;
    left: 50%;
    top: 89%;
    transform: translate(-50%, -50%);
    width: 400px;
    // background-color: rgb(46, 36, 36);
    height: 13%;


    .noteBtn11 {
        display: flex;

        justify-content: space-around;
border-radius: 15px;
        flex-wrap: wrap;
    }

    .noteBtn2 {
        display: flex;
        justify-content: space-around;
border-radius: 15px;
    }




    input {
        display: inline-block;
        height: 45px;
        // width: 120px;
        border: .5px solid #000;
       


    }
}

.end {
    #map {
        position: absolute;
        width: 40%;
        top: 20%;
        left: 20%;
        // transform: translate(20%);
    }
}


</style>
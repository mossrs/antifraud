<template>

    <div class="libraryUp" v-show="libraryUp">
        <img id="bg3" :src="libPath" alt="">
        <div class="return">
            <img class="returnBtn" src="../../img/导航栏/返回按钮.png" alt="" @click="re">
            <div class="returnTxt">
                返回
            </div>
        </div>
        <img v-show="msg" @click="open" class="msg1" src="../../img/scene01/phone/新短信.png" alt="">
        <div class="phone" v-show="phone">
            <img class="phoneImg" src="../../img/scene01/phone/短信.png" alt="">
            <div class="notetext" v-for="txt in text">

                <div class="text" v-show="txt1">{{txt.txt1}}</div>
                <div class="answer" v-show="txt2">{{txt.txt2}}</div>
                <div class="text" v-show="txt3">{{txt.txt3}}</div>

                <div style="width:200px;height:610px" v-show="txt4">
                    <div class="text" v-for="txt in text">
                        <img id="idImg" :src="txt.img" alt="" @click="zoomOut">
                    </div>
                </div>

                <div class="answer" v-show="txt5">
                    对的
                </div>
                <div class="text" v-show="txt6">
                    请根据短信中的网址修改信息后并缴纳500元的信息修改费，即可退出校园贷系统，拿回自己的学籍。 <b
                        style="text-decoration: underline; color: blue; cursor: pointer; "> https://学籍更改系统.com</b>
                </div>
            </div>
            <div class="noteBtn">
                <div class="noteBtn1" v-show="noteBtn1">
                    <input type="button" value="关闭界面" @click="close">
                    <input type="button" value="继续询问" @click="continue1">
                </div>
                <div class="noteBtn1" v-show="noteBtn2">
                    <input type="button" value="缴费关闭校园贷" @click="pay">
                    <input type="button" value="不予理睬" @click="close1">
                </div>
            </div>

        </div>

    </div>



</template>
<script setup lang='ts'>
import { onMounted, ref, nextTick, reactive } from 'vue';
import { gsap } from 'gsap';
import { setPersonaData } from '../../hooks';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
const router = useRouter();

const mytimeIn1 = ref(0);
const mytimeOut1 = ref(0);
type FraudData = {
    fraudResult: boolean;
    fraudType: string;
    hesitateTime: number;
}
const form = reactive<FraudData>({
    fraudResult: true,
    fraudType: "电信诈骗",
    hesitateTime: 0,     /*犹豫时间 单位毫秒*/
});

const emit = defineEmits(["onReceiveMsgLibrary", "onReceiveMsgLibraryMoney"]);
const msg = ref(true);
const phone = ref(true);

const libPath = ref("/pixi_res/scene01/library/library.png");
const libraryUp = ref(true)

const noteBtn1 = ref(false);
const noteBtn2 = ref(false);
const idImg = ref<HTMLElement | null>(null);

const txt1 = ref(true);
const txt2 = ref(false);
const txt3 = ref(false);
const txt4 = ref(false);
const txt5 = ref(false);
const txt6 = ref(false);

const scrollToBottom = () => {
    nextTick(() => {
        var div = document.getElementsByClassName('notetext')[0];
        div.scrollTop = div.scrollHeight;

    })
}

const open = (() => {
    const phoneShow = gsap.timeline();
    phoneShow.fromTo('.phone', 1.2, { width: 0,height:0, opacity: 0 }, { width: 500 + 'px',height:740+'px', opacity: 1 });
    setTimeout(() => {
        noteBtn1.value = true;
    },1500)
    msg.value = false;
})
const continue1 = (() => {
    noteBtn1.value = false;
    txt2.value = true;
    setTimeout(() => {
        txt3.value = true;
    }, 1000);
    setTimeout(() => {
        txt4.value = true;
        scrollToBottom();
    }, 1500)
})
const zoomOut = () => {
    if (idImg.value === null) {
        return
    }
  
    if (idImg.value.style.width == 150 + "px") {
        idImg.value.style.width = 340 + "px"
    } else {
        idImg.value.style.width = 150 + "px";
        scrollToBottom();
        setTimeout(() => {
            txt5.value = true;
            scrollToBottom();
        }, 2000)
        setTimeout(() => {
            txt6.value = true;
            scrollToBottom();
        }, 3200)
        setTimeout(() => {
            noteBtn2.value = true;
            scrollToBottom();
            mytimeIn1.value = +new Date;
        }, 3600)
    }
    
   
}
const text = [
    {
        txt1: "xx同学您好，我们发现您曾开通校园贷未取消，现需您配合注销账户，否则将影响您的个人征信。",
        txt2: "是不是弄错了，我没有开通过呀",
        txt3: "由于之前不谨慎留了信息，导致目前您的信息泄露给部分校园贷机构，这是您的学籍信息对吧？",
        img: "/pixi_res/scene01/library/学籍卡.png",
        txt4: "对的",

                   
    
       
    }
]


const pay = () => {
    mytimeOut1.value = +new Date;
    form.hesitateTime = mytimeOut1.value - mytimeOut1.value;
    form.fraudResult = true;
    const b = JSON.parse(JSON.stringify(form));
    setPersonaData({ data: b })
        .then(res => {
        console.log(b)
    }).catch(() => ElMessage("网络异常"))

    let datalist = sessionStorage.getItem("money");
    datalist = JSON.stringify((JSON.parse(datalist || '') - 500));
    sessionStorage.setItem("money", datalist);
    emit("onReceiveMsgLibraryMoney", datalist);

}
const close = (() => {
    phone.value = false;
   
    form.hesitateTime = mytimeOut1.value - mytimeOut1.value;
    form.fraudResult = false;
    const b = JSON.parse(JSON.stringify(form));
    setPersonaData({ data: b })
        .then(res => {
            console.log(b)
        }).catch(() => ElMessage("网络异常"))
})

const close1 = (() => {
    phone.value = false;
    mytimeOut1.value = +new Date;
    form.hesitateTime = mytimeOut1.value - mytimeOut1.value;
    form.fraudResult = false;
    const b = JSON.parse(JSON.stringify(form));
    setPersonaData({ data: b })
        .then(res => {
            console.log(b)
        }).catch(() => ElMessage("网络异常"))
})
onMounted(() => {
    idImg.value = document.getElementById("idImg")!;
    // setInterval(function () {
    //     const msgShake = gsap.timeline();
    //     msgShake.fromTo('.msg', .4, { translateY: 1, }, { translateY: -1, })
    //         // .to('.msg', .4, { translateY: 0,  })
    //         // .repeat(4)

    // }, 1500)
    
})

const re = (() => {
    libraryUp.value = false
    emit("onReceiveMsgLibrary", libraryUp.value);
    sessionStorage.setItem("fraud5", JSON.stringify(form));
    console.log("最后诈骗结果"+sessionStorage)
    // alert("0");
    router.push('/s2Index');
})

</script>

<style scoped lang='scss'>
#bg3{
    position: absolute;
    left: 0;
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
.erweima{
    position: absolute;
    // width: 60%;
    // margin-top: 80%;
    width: 100px;
    height: 100px;
    top: 70%;
    right: 10%;
    background-image: url('../../img/scene01/二维码.jpg');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    .line{
        position: absolute;
        width: 100%;
        height: 10px;
        left: 0;
        animation: scan 1.5s infinite alternate ;
        
    }
    @keyframes scan {
        from{
            top: 0;
        }
        to{
            top: 100%;
        }
        
    }
    .angle{
        position: absolute;
        width: 30px;
        height:30px;
        float: left;
        border: 4px solid #00ff33 ;
         
    }
    .angle1{
        left: 0;
        top: 0;
        border-right:none ;
        border-bottom: none;
    }
     .angle2{
        right: 0;
        top: 0;
        border-left:none ;
        border-bottom: none;
    }
    .angle3 {
        right: 0;
        bottom: 0;
        border-left: none;
        border-top: none;
    }
    .angle4 {
        left: 0;
        bottom: 0;
        border-right: none;
        border-top: none;
    }


    
}
.msg1 {
    position: absolute;
    width: 18%;
    position: fixed;
    bottom: 200px;
    left: -20%;
    cursor: pointer;
    z-index: 100;
    // width: 100px;
  
        animation: trans 2s 1.4s forwards ease;
}
@keyframes trans {
    from{
        left: -20%;
        opacity: 0;
    }
    to{
        left: 2%;
        opacity: 1;
    }
    
}

.phone{
    position:relative;
    width: 0px;
 
    height: 0px;
    left: 50%;
    transform: translate(-50%);
    position: fixed;
    bottom: 0;
    // background-color: #fff;

    .phoneImg{
        position: absolute;
        width: 100%;
        transform: translate(-50%);
    }
    .notetext{
        position: absolute;
        background-color: rgb(236,236,236);
        height: 70%;
        width: 80%;
        bottom: 10%;
        transform: translate(-50%);
        left: 50%;
        overflow: scroll;
        .text,.answer {
        background-color: #fff;
            padding: 12px;
            padding-left: 17px;
            border-radius: 0 13px 13px 13px;
            font-size: 18px;
            float: left;
            text-align: left;
            padding: 12px;
            margin-top: 20px;
            #idImg {
                    margin-top: 20px;
                    width: 150px;
                    // height: 200px;
                    cursor: pointer;
            
            
            
                }
           
        }
        
           
          
        
        .answer{
            float:right;
            
            border-radius:  13px 0 13px 13px;
          
            background-color: rgb(206, 230, 201);
                
        }
    }
    .noteBtn{
        position: absolute;
        left: 50%;
        // top: 89%;
        transform: translate(-50%, -50%);
        width: 400px;
        // background-color: rgb(46, 36, 36);
        height: 80px;
        bottom: -4.8%;
        padding-top: 10px;
     .noteBtn1{
        display: flex;
        justify-content: space-around;

     }
        
      
        input {
            
        display: inline-block;
        height: 60px;
        // width: 120px;
        border: .5px solid #000;
        border-radius: 20px;
        font-size: 18px;
        padding: 0 20px;
        cursor: pointer;
        margin-top: 10px;
        }
    }
    }
</style>
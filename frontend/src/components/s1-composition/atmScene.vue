<template>
    <div v-show="ATMup" class="ATMup">

        <img id="bg2" :src=atmpath alt="">
        <div class="return">
            <img class="returnBtn" src="../../img/导航栏/返回按钮.png" alt="" @click="re">
            <div class="returnTxt">
                返回
            </div>
        </div>
        <img v-show="arrow" id="arrow" src="../../img/scene01/提示标.png" @click="inatm" alt="">
        <img v-show="arrow" id="circle1" src="../../img/scene01/circle.png" alt="" @click="inatm">
        <img v-show="accountBtn" id="tansferAccount" :src=atmBtnpath @click="transfer">

        <div class="bord" v-show="bord">
            <input id="atminput" type="text" value="">
            <div class="txt">请输入转账金额</div>
            <div class="key">
                <ul>
                    <li onclick="">1</li>
                    <li onclick="">2</li>
                    <li onclick="">3</li>
                    <li onclick="">4</li>
                    <li onclick="">5</li>
                    <li onclick="">6</li>
                    <li onclick="">7</li>
                    <li onclick="">8</li>
                    <li onclick="">9</li>
                    <li onclick="">.</li>
                    <li onclick="">0</li>
                    <li onclick=""> </li>

                </ul>
            </div>
        </div>
        <div class="bord1" v-show="bord1">
            <div class="top">请确认转账交易信息</div>

            <div class="bottom">
                <div class="txt">转账</div>
                <ul>
                    <!-- <li id="firstLi"> 转账</li> -->
                    <li class="red">转入卡号：</li>
                    <li>232323</li>
                    <li class="red">收款人：</li>
                    <li>周**</li>
                    <li class="red">转入金额：</li>
                    <li id="account">3000.00</li>
                </ul>
            </div>
        </div>

        <div class="bord2" v-show="bord2">
            <div class="top"></div>

            <div class="bottom">
                转账成功，完成交易！
            </div>
        </div>


        <img v-show="phone1" class="phone1" :src="pathPhone" alt="">

        <div v-show="showMsg" class="message">

            <img id="pop" src="../../img/scene01/通话框.png" alt="">
            <img v-show="msg" @click="answer" class="msg" src="../../img/scene01/phone/短信提示.png" alt="">

            <div class="text">


            </div>
            <div class="Btn">

            </div>

            <div class="notetext" v-show="notetext">
                <div class="n1 nn" v-for="note in noteItems">{{ note.note1 }}</div>
                <!-- <div class="b1 bb" v-for="btn in btns" v-show="b1">{{ btn.btn1 }}</div> -->
                <div class="nn erweima">
                    <div class="line">
                        <div class="scan" v-show="scan1">
                            <div class="line1">

                            </div>
                            <div class="angle"></div>
                            <div class="angle angle2"></div>
                            <div class="angle angle3"></div>
                            <div class="angle angle4"></div>

                        </div>
                    </div>

                </div>
            </div>
            <div class="noteBtn">
                <div class="noteBtn11 noteBtn1" v-show="noteBtn1">
                    <input class="inputBtn1" type="button" value="扫描二维码完善信息，以免影响入学" @click="scan">
                    <input class="inputBtn1" type="button" value="先问一下辅导员再决定" @click="exict3">
                </div>

            </div>

        </div>

        <div class="bord3" v-show="bord3">
            <img id="banner1" :src="chart1" alt="">
            <div class="part1" v-show="part1">
                <div class="input1">
                    <el-form-item label="姓名">
                        <el-input v-model="form.name" />
                    </el-form-item>
                    <el-form-item label="学号">
                        <el-input v-model="form.id" />
                    </el-form-item>
                    <el-form-item label="入学年份">
                        <el-select placeholder="请选择" v-model="form.year">
                            <el-option label="2019" value="2019" />
                            <el-option label="2020" value="2020" />
                            <el-option label="2021" value="2021" />
                            <el-option label="2022" value="2022" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio-group v-model="form.gender">
                            <el-radio label="男" />
                            <el-radio label="女" />
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="专业">
                        <el-select v-model="form.area" placeholder="请选择">
                            <el-option label="计算机科学与技术" value="计算机科学与技术" />
                            <el-option label="软件工程" value="软件工程" />
                            <el-option label="英语" value="英语" />
                            <el-option label="法学" value="法学" />
                            <el-option label="材料物理" value="材料物理" />

                        </el-select>
                    </el-form-item>
                </div>
                <div class="bottom">
                    <img class="nextBtn" @click="next1" src="../../img/scene01/atm/下一步.png" alt="">
                </div>
            </div>
            <div class="part2" v-show="part2">
                <div class="input1">
                    <el-form-item label="身份证号">
                        <el-input v-model="form.sfz" />
                    </el-form-item>
                    <el-form-item label="银行卡号">
                        <el-input v-model="form.account" />
                    </el-form-item>
                    <el-form-item label="预留手机号">
                        <el-input v-model="form.phone" />
                    </el-form-item>





                </div>
                <div class="bottom">
                    <img class="nextBtn" @click="next2" src="../../img/scene01/atm/下一步.png" alt="">
                </div>
            </div>
            <div class="part3" v-show="part3">
                <div class="input1">
                    <h4 style="color:#96313">请确认缴费金额</h4>
                    <el-form-item label="缴纳学费：">
                        3000.00 元
                    </el-form-item>
                    <el-form-item label="输入密码">
                        <el-input type="password" v-model="form.psw" />
                    </el-form-item>




                </div>
                <div class="bottom">
                    <img class="nextBtn" @click="next3" src="../../img/scene01/atm/下一步.png" alt="">
                </div>
            </div>
            <div class="part4" v-show="part4">
                <img style="margin-top: 25%;" src="../../img/scene01/atm/完成办理.png" alt="">
                <div class="bottom">
                    <img class="nextBtn nextBtn1" @click="exict" src="../../img/scene01/atm/完成.png" alt="">
                </div>
            </div>

        </div>

    </div>
</template>


<script setup lang='ts'>
import { ref, onMounted } from 'vue'
import { gsap } from 'gsap';
import { reactive } from 'vue'
import { setPersonaData } from '../../hooks';
import { ElMessage } from 'element-plus';


const mytimeIn1 = ref(0);
const mytimeOut1 = ref(0);

const phone1 = ref(true);


// do not use same name with ref
type FraudData = {
    fraudResult: boolean;
    fraudType: string;
    hesitateTime: number;
    name: string;
    gender: string;
    area: string;
    sfz: string;
    account: string;
    phone: string;
    psw: string;
    id: string;
    year:string
    
}
const form = reactive<FraudData>({
    fraudResult: true,
    fraudType: "泄露隐私诈骗",
    hesitateTime: 0,     /*犹豫时间 单位毫秒*/
    name: '',
    gender: '',
    area: '',
    sfz:'',
    account: '',
    phone: '',
    psw: '',
    id: '',
    year:''
    
});



const onSubmit = () => {
    console.log('submit!')
}
const ATMup = ref(true);


const emit = defineEmits(["onReceiveMsg", "onReceiveATMMoney"]);


const atmBtnpath = ref("/pixi_res/scene01/atm/转账.png")
const atmpath = ref("/pixi_res/scene01/场景4交学费.png");
const arrow = ref(true);  //atmarrow
const chart1 = ref("/pixi_res/scene01/atm/第一步.png");


//转账界面
const bord = ref(false);  //atm转账
const bord1 = ref(false);  //atm转账
const bord2 = ref(false);  //atm转账
const bord3 = ref(false);  //atm转账

//转账按钮
const accountBtn = ref(false);

//转账选择
const noteBtn1 = ref(false);

const scan1 = ref(false);


//短信
const pathPhone = ref("/pixi_res/scene01/phone/短信.png")
const showMsg = ref(false);
const msg = ref(true);
const notetext = ref(false);

const part1 = ref(true);
const part2 = ref(false);
const part3 = ref(false);
const part4 = ref(false);
// 点击短信
const answer = () => {


    msg.value = false;

    const phoneShow = gsap.timeline();
    phoneShow.fromTo('.phone1', 1.2, { width: 0, opacity: 0 }, { width: 510, opacity: 1 });
    setTimeout(function () {
        notetext.value = true;
    }, 1500)
    setTimeout(function () {
        noteBtn1.value = true;
        mytimeIn1.value = + new Date;
        let datalist = JSON.stringify(JSON.parse(sessionStorage.getItem("money") || '') + 3000);
        sessionStorage.setItem("money", datalist);
        console.log(sessionStorage)
        emit("onReceiveATMMoney", datalist);
    }, 3500)


}
//短信内容
const noteItems = [
    {
        note1: "【退款提醒】尊敬的客户您好，作为在校新生，您的账户并没有激活，无法正常存取款的，属于违规操作，现已将您缴纳的3000元退还回您的账户，四小时内会到账，"
            + "另外，请您扫描二维码重新完善学籍信息和个人信息，再直接从完善信息的界面缴纳学费，请继续认证，"


    }

]



const inatm = () => {
    atmpath.value = "/pixi_res/scene01/ATM.png";
    arrow.value = false;
    bord.value = true;


    accountBtn.value = true;
}
const transfer = () => {
    if (bord.value) {
        bord.value = false;
        atmBtnpath.value = "/pixi_res/scene01/atm/确认.png";
        bord1.value = true;

       
    } else {
        bord1.value = false;
        bord2.value = true;
        accountBtn.value = false;
        setTimeout(function () {
            showMsg.value = true;
           
        }, 2000)
        let datalist = JSON.stringify(JSON.parse(sessionStorage.getItem("money") || '') - 3000);
        sessionStorage.setItem("money", datalist);
        console.log(sessionStorage)
        emit("onReceiveATMMoney", datalist);

    }

}

//扫描二维码
const scan = () => {
    mytimeOut1.value = +new Date;
    form.fraudResult= true;
    form.hesitateTime = mytimeOut1.value - mytimeIn1.value;
    let b = JSON.parse(JSON.stringify(form));
    setPersonaData({data:b})
        .then(res => {
    console.log(b)
}).catch(() => ElMessage("网络异常"))
    scan1.value = true;
    setTimeout(() => {
        showMsg.value = false;
        bord3.value = true
        pathPhone.value = "/pixi_res/scene01/atm/交学费.png"
    }, 3500);

}
//退出
const exict3 = () => {

    showMsg.value = false;
    phone1.value = false;
    mytimeOut1.value = +new Date;
    form.fraudResult = false;
    form.hesitateTime = mytimeOut1.value - mytimeIn1.value;

    let b = JSON.parse(JSON.stringify(form))
    setPersonaData({data:b})
        .then(res => {
            console.log(b)
        }).catch(() => ElMessage("网络异常"))

    
}
const next1 = () => {
    chart1.value = "/pixi_res/scene01/atm/第二步.png";
    part1.value = false;
    part2.value = true;



}
const next2 = () => {
    chart1.value = "/pixi_res/scene01/atm/第三步.png";

    part2.value = false;
    part3.value = true;

    let datalist = JSON.stringify(JSON.parse(sessionStorage.getItem("money") || '') - 3000);
    sessionStorage.setItem("money", datalist);
    console.log(sessionStorage)
    emit("onReceiveATMMoney", datalist);

}
const next3 = () => {
    chart1.value = '';
    chart1.value = "/pixi_res/scene01/atm/第四步.png";
    part3.value = false;
    part4.value = true;



}


//退出
const exict = () => {
    bord3.value = false;
    pathPhone.value = ""
   


}
const re = () => {
    ATMup.value = false;
    emit("onReceiveMsg", ATMup.value);
    sessionStorage.setItem("fraud4", JSON.stringify(form));
}

onMounted(() => {
     setInterval(function () {
        const timeline = gsap.timeline();
        timeline.fromTo('.msg', .08, { rotateZ: 3.5, }, { rotateZ: -3.5 })
            .to('.msg', .08, { rotateZ: 3.5, })
            .repeat(6)
    }, 1700);

    const btn = document.getElementsByTagName("li");
    const input:any = document.getElementById("atminput");
    const account:any = document.getElementById("account");
    var str = "";
    for (let i = 0; i < btn.length; i++) {
        btn[i].addEventListener("click", () => {
            let val = btn[i].innerHTML;
            console.log("00");
            if (input.value === "") {
                input.value = val;
                str = val;
            } else {
                str += val;
                input.value = str;
                account.innerHTML = input.value + ".00";
            }

        })
    }

    const atmArr = gsap.timeline();
    atmArr.to("#circle1", 1.2, { scale: 1.6 })
        .to("#circle1", 1.2, { scale: 1 })
        .repeat(300)

       const atmArr2 = gsap.timeline();
    atmArr2.to("#arrow", 1.2, { top: 88+'%'})
        .to("#arrow", 1.2, { top:86+'%' })
        .repeat(300)

})



</script>
<style scoped lang='scss'>
.bgImg {

    position: absolute;
    width: 100;
    height: 100%;
    background: url('../../img/scene01/校门口.png') no-repeat;
    ;
    background-size: 100%;
    z-index: -100;
}

.phone1 {

    // position: fixed;
    position: absolute;
    width: 0;
    opacity: 0;
    left: 50%;
    transform: translate(-50%);
    bottom: -2%;

}


.msg {
    position: absolute;
    width: 18%;
    position: fixed;
    bottom: 10px;
    left: 5%;
    cursor: pointer;
}



#pop {
    position: absolute;
    width: 0;
    height: 11%;
    transform: translate(-50%);
    top: 8%;
    z-index: -10;
}

.note {
    // width: 20%;
    height: 0;
    position: absolute;
    top: 23%;
    transform: translate(-50%);
    cursor: pointer;
}

//字幕
.text {

    font: 36px bold;
    position: fixed;
    // background-color: #fff;
    width: 100%;
    top: 96px;
    text-align: center;
    z-index: -5;
}

.inputBtn1 {

    text-align: center;
    padding: 0 10px;
    border-radius: 10px;
    display: block;
    margin-top: 20px;
    cursor: pointer;

    // border-style: outset;
    font-size: 18px;
}



.notetext {
    position: absolute;
    top: 61%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 330px;
    // background-color: rgb(124, 84, 84);
    width: 330px;
    // display: flex;
    height: 450px;
    // justify-content: flex-end;



    .nn {

        background-color: #fff;
        padding: 10px;
        border-radius: 0 13px 13px 13px;
        font-size: 17px;
        float: left;
        text-align: left;

    }

    .bb {
        // width: 330px;
        background-color: rgb(206, 230, 201);
        padding: 10px;
        border-radius: 13px 0 13px 13px;
        font-size: 17px;
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
        height: 170px;



    }

    .n2 {
        margin-top: 20px;
        height: 240px;
    }



}

.noteBtn {
    position: absolute;
    left: 50%;
    top: 85%;
    transform: translate(-50%, -50%);
    width: 400px;
    // background-color: rgb(46, 36, 36);


    .noteBtn11 {
        display: flex;

        justify-content: space-around;

        flex-wrap: wrap;
    }

    .noteBtn2 {}




    .inputBtn1 {
        display: inline-block;
        height: 50px;
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

// .line {

//     width: 20%;
//     height: 20%;

// }
.line {
    position: relative;

    // background-color: #000;
    width: 136.8px;
    height: 136.8px;

    // -webkit-animation: myScan 1s infinite alternate;
    z-index: 2;
    background: url('../../img/scene01/二维码.jpg') no-repeat;
    background-size: cover;
}



.line1 {
    position: absolute;
    background: url('../../img/scene01/atm/扫描线.png') no-repeat;
    // background-size: cover;
    z-index: 20;
    width: 100%;
    height: 60%;
    // background-color: #000;
    animation: myScan 1s infinite alternate;
}

@keyframes myScan {
    from {
        top: 100%;
    }

    to {
        top: 0%;
    }
}

.angle {
    content: '';
    display: block;
    position: absolute;
    width: 2vw;
    height: 2vw;

    border: 0.2rem solid transparent;
    // right: 0;
    // border-right-color: #00ff33;
    border-left-color: #00ff33;
    // border-bottom-color: #00ff33;
    border-top-color: #00ff33;
}

.angle2 {
    float: left;
    right: 0;
    border-left: none;
    border-right-color: #00ff33;
    border-top-color: #00ff33;
    border-bottom: none
}

.angle3 {
    float: left;
    bottom: 0;
    border-top: none;
    border-bottom-color: #00ff33;
    border-left-color: #00ff33;
    border-right: none
}

.angle4 {
    float: left;
    right: 0;
    bottom: 0;
    border-left: none;
    border-right-color: #00ff33;
    border-bottom-color: #00ff33;
    border-top: none
}



.erweima {
    margin-top: 20px;
    // width: 60%;
    // height: 30%;
    // background: url('../../img/scene01/二维码.jpg') no-repeat;
    // background-size: cover;
}

#erweima {
    width: 100%;
}

.bord3 {
    position: absolute;
    width: 400px;
    height: 610px;
    background-color: #fff;
    left: 50%;
    bottom: 6%;
    transform: translate(-50%);

    #banner1 {
        width: 80%;
        margin-top: 5%;
    }

    .input1 {
        width: 70%;
        margin: 0 auto;
        margin-top: 10%;

        .el-input {

            // border-bottom: 1px solid rgb(240, 136, 38);
            border: none;
        }



    }

    .nextBtn {
        width: 70%;
        margin-bottom: -50%;
        cursor: pointer;
    }

    .nextBtn1 {

        margin-bottom: -35%;

    }
}

.ATMup {
    #bg2 {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0;
    }

    #arrow {
        position: absolute;
        top: 86%;
        left: 42%;
        cursor: pointer;

    }

    #circle1 {
        position: absolute;
        top: 92%;
        left: 41.5%;
        cursor: pointer;
    }

    #tansferAccount {
        position: absolute;
        top: 50.6%;
        left: 49%;
        width: 4%;
        cursor: pointer;
    }

    .bord {
        position: absolute;
        width: 14%;
        height: 18.5%;
        background-color: rgb(218, 220, 222);
        left: 36%;
        top: 31%;
        border: 1px solid #000;
        border-radius: 6px;
        padding-bottom: .3%;
        // display: none;

        input {
            width: 80%;
            height: 13%;
            border: .4px solid;
            border-radius: 6px;
            margin-top: 2.5%;
            text-align: left;
        }

        .txt {
            // background-color: rgb(66, 55, 55);
            // float: left;
            width: 87%;
            margin: 0 auto;
            font-size: .7rem;
            text-align: left;
            margin-left: 9%;
            color: rgb(107, 107, 107);
        }

        .key {

            // background-color: rgb(121, 180, 98);
            height: 75%;
            // float: left;
            // left: -1%;
            width: 100%;
            border-top: 1px solid #000;
            // height: 100%;
        }

        ul {
            // text-align: center;
            height: 173px;
            position: relative;
            margin-top: 5px;
            // margin: 0 auto;
            float: left;
            margin-left: -6.8%;
            width: 87%;
        }

        li {
            width: 30%;
            height: 14.3%;
            float: left;
            border: 1px solid;
            left: 45%;
            background-color: rgb(152, 159, 160);
            border-radius: 10px;
            margin: 2px;
            user-select: none;
            // text-align: center;
            line-height: 25px;
            cursor: pointer;
        }

        li:hover {
            background-color: rgb(125, 130, 131);

        }
    }

    .bord1 {
        position: absolute;
        width: 14%;
        height: 18%;
        background-color: rgba(224, 226, 230, 0.651);
        left: 36%;
        top: 31%;

        border-radius: 5px;

        .top {
            height: 14%;
            width: 100%;
            background-color: rgb(170, 73, 73);
            color: #fff;
            text-align: 14%;
            padding: 2px 0;
            border-radius: 5px 5px 0 0;
        }

        .bottom {

            height: 76%;


            width: 100%;
            // background-color: rgb(199, 60, 60);
            padding: 0;
            margin: 0;
        }

        ul {

            width: 100%;

            margin: 0 auto;
            margin-top: 3%;
            margin-left: -5%;
        }

        li {
            float: left;
            width: 40%;
            height: 40%;
            left: 0;
            // background-color: #fff;
            user-select: none;
            // text-align: center;
            line-height: 25px;
            cursor: pointer;
        }

        .red {
            color: rgb(204, 99, 99);
        }

        .txt {
            font-size: 20px;
            color: rgb(199, 53, 53);
            margin-top: 4%;
            font-weight: 600;
        }



    }

    .bord2 {
        position: absolute;
        width: 14%;
        height: 18%;
        background-color: rgba(224, 226, 230, 0.651);
        left: 36%;
        top: 31%;

        border-radius: 5px;

        .top {
            height: 14%;
            width: 100%;
            background-color: rgb(170, 73, 73);
            color: #fff;
            text-align: 14%;
            padding: 2px 0;
            border-radius: 5px 5px 0 0;
        }

        .bottom {
            height: 76%;


            width: 100%;
            // background-color: rgb(199, 60, 60);
            padding: 0;
            margin: 0;
            font-size: 20px;
            color: rgb(199, 53, 53);
            margin-top: 12%;
            font-weight: 600;
            text-align: center;


        }
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
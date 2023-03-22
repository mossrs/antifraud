<template>

    <div class="container">
        <div class="return">
            <img class="returnBtn" src="../../img/导航栏/返回按钮.png" alt="" @click="re">
            <div class="returnTxt">
                返回
            </div>
        </div>

        <div class="mainbox" @mousewheel="handleScroll">
            <div class="askPage" v-for="(item,index) in data" :key="index">

                <div class="fraudOptionType">

                    {{ index + 1 }}
                    [{{ item.type }}]
                    <span v-show="item.answerShow" :style="item.isTrue ? 'color:green' : 'color:red'" id="rightAnswer">
                        <span v-if="!item.isTrue"> 回答错误！</span>
                        <span v-if="item.isTrue"> 回答正确！</span>
                        正确答案：{{ item.answer }}
                    </span>


                </div>
                <div class="fraudMain">

                    <span class="fraudType">[{{ item.fraudType }}]</span>
                    <span class="fraudType">[{{ item.tag }}]</span>
                    &nbsp; <span>{{ item.content }}</span>
                </div>
                <div class="fraudOptions">
                    <br />
                    <div class="fraudOption" v-for="option in item.options"
                        :class="option.checked ? 'fraudOption_active' : 'fraudOptions'"
                        @click="handleClick(option.checked, option.id, option, item.type, item.options,item.tag)">

                        {{ option["name"] }}

                    </div>
                </div>



                <div class="submitBtn" @click="submit(item.answer,item)">

                    提交
                </div>



                <!-- {{item}} -->
            </div>
        </div>
        <div id="tag">
            <span id="tagTip">选项</span>
            <div class="tagBtn" @click="handleTagClick(0)">1</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(1)">2</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(2)">3</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(3)">4</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(4)">5</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(5)">6</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(6)">7</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(7)">8</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(8)">9</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(9)">10</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(10)">11</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(11)">12</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(12)">13</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(13)">14</div>
            <div class="tagPoint"></div>
            <div class="tagBtn" @click="handleTagClick(14)">15</div>
        </div>
    </div>

</template>
<script setup lang='ts'>
import { getScene02Test, AxiosResponse, Result } from '../../hooks';
import { onMounted, reactive , ref} from 'vue';
import axios from 'axios'
import { OptionProps } from 'element-plus/es/components/select-v2/src/defaults';
import Navabar from '../s1-composition/Navabar.vue';

export interface FraudDatas{
    data:string[]
}

type FraudData = {
    ID:number,
      id: number;
      content: string;
      type: string;
      fraudType: string;
      tag: string[];
      answer: string;
    options:[
        { id: number, name: string, checked: boolean; }
    ];
    checked: boolean;
    choose: number;
    name: string;
    multipleAnswer: string[],
    isTrue: boolean,
    answerShow:boolean
    
    
}
type dataType = {
    scenename: string,
    money: string
};

const datalist = reactive<dataType>({
    scenename: "答题测试",
    money: sessionStorage.getItem("money") || ''
})
var fraudOption_active = "fraudOption_active";

const fraudList = reactive<FraudData>({
    name:"",
    isTrue: false,
    answerShow:false,
    content:"",
    type: "",
    fraudType:"",
    tag:[],
    answer:"",
    options: [
        {
            id: -1,
            name: "",
            checked:false
            
        }
       
    ],
    checked: false,
    choose: -1,
    id: -1,
    ID: -1,
    multipleAnswer:[]
    
    
})

const emit = defineEmits(["onReceiveTest"])
const ischecked = ref(false);
const idx = 0;
var div;
var tag;
var askPage = 0

onMounted(() => {
    div = document.getElementsByClassName("mainbox")[0];
    tag = document.getElementsByClassName("tagBtn") ;
   
})

const data = ref<FraudData[]>([]);
var multipleAnswer = [] as any[];
var wheelFlag = false;
const handleClick = ((checked,optionIndex,option,optionType,optionList,tag) => {
 
    
    console.log("optionList:" + JSON.stringify(optionList));
  console.log("标签",tag)
    // console.log("optionIndex:" + optionIndex);
    // console.log("optionList:" + optionList);
    console.log("optionType:" + optionType);
    if (optionType == "多选题") {
        if (!option.checked) {  //选择
            option.checked = !option.checked;
            multipleAnswer.push(option.name[0]);

        } else {
            option.checked = !option.checked;

            multipleAnswer = multipleAnswer.filter(item => item != option.name[0])

        } 
    } else {
        if (!option.checked) {
            optionList.forEach(item => {
                item.checked = false;
              
              
            })
            option.checked = !option.checked;
            multipleAnswer.push(option.name[0])
        } else {
            option.checked = !option.checked
            multipleAnswer = multipleAnswer.filter(item => item != option.name[0])
        }
       
    }

    // if (optionType == "多选题") { 
    //     optionList.forEach(item => {
    //         if (item.checked) {
    //             multipleAnswer.push(item.name[0]);
    //         }
    //     })
        
    // }
    console.log("multipleAnswer"+multipleAnswer);


    // options.forEach(item => {
    //     if (nowOption == item) {  //选中
    //         console.log("选中：" + JSON.parse(JSON.stringify(nowOption)));
    //     }


})
const submit = ((Answer, item) => {
    
   
    let id = div.scrollTop / 680;
    
   
    
    multipleAnswer = multipleAnswer.sort();
    let i = 0;
    if (Answer.length == multipleAnswer.length) {

        multipleAnswer.forEach(item1 => {
            if (item1 != Answer[i]) {
                
                console.log("Answer[i]" + Answer[i])
                item.isTrue = false;     
                tag[id].style.backgroundColor = 'rgb(207 53 53)';         
                return
            }
            i += 1;

        })
        if (i == Answer.length) {
            item.isTrue = true;
            tag[id].style.backgroundColor = 'rgb(39 211 120)';
        }


    } else {
        item.isTrue = false;
        tag[id].style.backgroundColor = 'rgb(207 53 53)';
    }

    
    item.answerShow = true;

  
    multipleAnswer = [];
    

})

const handleScroll = (e => {
    console.log(e.deltaY);
    console.log(div.scrollTop);
    if (!wheelFlag) {
        wheelFlag = true;
        if (e.deltaY > 0 && askPage < 14) {
            askPage ++;
        } else if (e.deltaY < 0 && askPage > 0) {
            askPage --;
        }
        runAnimation();
    }
   
})

const runAnimation = (() => {
    let timer = setInterval(function () {
        if (div.scrollTop == askPage * 680) {
            wheelFlag = false;
            clearInterval(timer);
        }
        else if (div.scrollTop < askPage * 680) {
            div.scrollTop += 20;
        }
        else if (div.scrollTop > askPage * 680) {
            div.scrollTop -= 20;
        }

    },10)

})

const handleTagClick = ((page) => {
    askPage = page;

    runAnimation();
})

axios.all([
    axios.get("http://101.35.247.14:8081/antifraud/stus/fraudSubject/list", {
    params: {
        fraudType: "新型诈骗"
    },
    }),
    axios.get("http://101.35.247.14:8081/antifraud/stus/fraudSubject/list", {
        params: {
            fraudType: "电信诈骗"
        },
    }),
    axios.get("http://101.35.247.14:8081/antifraud/stus/fraudSubject/list", {
        params: {
            fraudType: "冒充熟人诈骗"
        },
    }),
    axios.get("http://101.35.247.14:8081/antifraud/stus/fraudSubject/list", {
        params: {
            fraudType: "信用卡诈骗"
        },
    }),
    axios.get("http://101.35.247.14:8081/antifraud/stus/fraudSubject/list", {
        params: {
            fraudType: "网络诈骗 "
        },
    }),

])
    .then((response) => {
        var newArry = [] as any[];
        for (let i = 0; i < response.length; i++){
            const res: Result = response[i].data;
            var rows = res.data as unknown as any[];
            newArry = newArry.concat(rows);
           
        }
        console.log(newArry)
       
    // let checked = JSON.parse(JSON.stringify('{ "checked": false }'));
        data.value = newArry.map(val => {
        let _options = [
            JSON.parse(JSON.stringify(
            {
                id: 0,
                name: val.aoption,
                checked: false
            })),
            {
                id: 1,
                name: val.boption,
                checked: false
            },
            {
                id: 2,
                name: val.coption,
                checked: false
            },
            {
                id: 3,
                name: val.doption,
                checked: false
            }


            ];
          
            const options = _options.filter(option => 
                option && option.name !== null && option.name !== ''
               
        );
            console.log(options)
        var ID = 0;
        ID += 1;
        let Choose = -1;
        let isTrue = false;
        let answerShow = false;

        return {
            choose:Choose,
            ID,
            content: val.fraudSubjectName,
            id:val.id,
            options,
            type: val.fraudOptionType,
            fraudType: val.fraudType,
            answer: val.answer,
            tag: val.fraudTag,
            isTrue,
            answerShow
        } as FraudData
       

          
        

    })
  
})
const re = () => {
    emit("onReceiveTest",false)
}

    // let question = ;
    // console.log(question);
   

    // console.log(ischecked.value);



</script>
<style scoped lang='scss'>



.container{
    width: 100%;
    height: 100%;
    min-width:1300px;
    min-height: 1080px;
    background-color: rgb(173, 216, 226);
    // position: relative;
    text-align: center;
    // overflow: hidden;
    background-image: url('../../img/scene02/答题背景.png') ;
 
 
}
.Top{
    margin: 0 auto;
    height: 85px;
    line-height: 85px;
    width: 350px;
    border-radius: 25px;
    background-color:rgb(238, 223, 164);
    border: 2px solid rgb(227, 209, 137);
        border-style: inset;
        color: #fff;
        font-weight: 700;
        font-size: 38px;
        font-family: SourceHanSansSC-Light, Source Han Sans SC;
        letter-spacing: .8px;
        margin-bottom: 50px;
        margin-top: 50px;

}
.mainbox{
    position: absolute;
    width: 950px;
    height: 680px;
    border-radius: 35px 0px 0 35px;
    background-color: rgba(255, 255, 255, 0.849);
    text-align: left;
    // margin: 0 auto;
    padding: 10px 25px;
    margin-top: 11%;
    overflow: hidden;

    left: 45%;
    transform: translate(-50%);
    box-shadow: 0 0 15px rgb(168, 168, 168) inset;
    transition-duration: 2s;
/*     以下三值为默认值，稍后会详细介绍 */
    transition-property: all;
    transition-timing-function: ease;
    transition-delay: 0s;
.askPage{
height: 100%;
width: 880px;
overflow: hidden;
}
    .fraudOptionType{
        font-size: 1.4rem;
        text-align: left;
        margin-bottom: 12px;
        margin-top: 30px;
        color: rgb(235,49,30);
        font-weight: 700;
  
        // background-color: rgb(136, 109, 109);
    }
    .fraudMain{
     font-size: 1.6rem;
     margin-bottom: 22px;
     height: 155px;
    font-weight: 500;
    background-color: rgba(226, 226, 226, 0.603);
    padding: 10px 20px;
    border-radius: 15px;
  
     .fraudType{
        font-size: 1.05rem;
        font-weight: 800;
        margin-right: 3px;
     }
    
     
    }
    .fraudOptions{
        // background-color: rgb(223, 159, 159);
        height: 337px;
        
      
      
        .fraudOption {
    display: flex;
                //  background-image: url('../../img/scene01/dialogBox/选项1.png') ;
                background-size: 100% 100%;

                // width: 600px;
                // background-color: rgb(160, 137, 137);
                cursor: pointer;
            font-size: 20px;
                margin-bottom: 11px;
            
              
                    // display: block;
                    // *zoom: 1;
               height: 66px;
              
            line-height: 66px;
            padding: 0 50px;
            border: solid 1px #000;
            border-radius: 15px;
            // background-color: rgb(201, 180, 180);
        
            
            }
        .fraudOption_active{
            background-color: rgb(240,125,38);
        }
    
    }
    #rightAnswer{
        margin-left: 60px;
        color: rgb(69,194,67);
        height: 20px;
        // width: 200px;
    //    position: absolute;
  
   

    }
    .submitBtn{
    width: 180px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    // background-color: rgb(184, 148, 148);
    background-image: url('../../img/scene01/dialogBox/选项3.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    cursor: pointer;

    margin: 0 auto;
    margin-top: 16px;
    font-size: 21px;
    font-weight: 550;
    }
}

.tag {
    background-color: rgb(128, 88, 88);
    color: black;
}

.tag_active {
    color: white;
    background-color: rgb(223, 222, 222);
}

#tagTip {
    color: #727272;
    font-size: 15px;
    font-family: SourceHanSansSC-Light, Source Han Sans SC;
    font-weight: 300;
    display: block;
    margin-bottom: 40px;
}

.tagBtn {
    display: block;
    width: 45px;
    height: 45px;
    border-radius: 45px;
    line-height: 45px;
    margin: 0 auto;
    color: #727272;
    font-size: 13px;
    border: 1px solid #b1b1b1;
    font-family: SourceHanSansSC-Light, Source Han Sans SC;
    font-weight: 300;
    box-shadow: rgb(255, 255, 255) -6px 6px 17px inset;
    background-color: rgb(238, 238, 238);
    opacity: 0.8;
    margin-bottom: 10px;
    cursor: pointer;
}

.tagPoint {
    width: 1px;
    height: 10px;
    display: block;
    border-right: 1px #b1b1b1 dotted;
    margin: 0  3px auto auto;
}
#tag {
    width: 180px;
    height: 620px;
    border-radius: 0 35px 35px 0;
    padding-top: 80px;
    padding-right: 8px;
    float: right;
    margin-right: 18%;
    margin-top: 11%;
    div{
        display: inline-block;
    }
    background-color: rgba(255, 255, 255, 0.699);
   box-shadow: 0 0 15px rgb(156, 156, 156) inset;
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
<template>
    <div>
        <img class="bgImg" src="../img/Home/bg1.png" alt="" />
        <img class="envelope" @click="open" src="../img/Home/信息提示框.png" alt="" />
        <img class="news1" @click="open" src="../img/Home/1.png" alt="" />
        <img class="hand" src="../img/Home/hand.png" alt="" />
        <div class="container" v-show="next">
            <img class="letter" src="../img/Home/录取通知书.png" alt="" />
            <img class="btn" @click="goto" src="../img/Home/按钮-去报到.png" alt="" />
        </div>
    </div>
</template>
<script setup lang='ts'>
import { gsap } from 'gsap';
import { onMounted } from 'vue';
import { ref } from 'vue';
import { useRouter } from "vue-router";
const router = useRouter();
const next = ref(false);
onMounted(() => {

    const timeline = gsap.timeline();
    timeline.fromTo('.news1', .7, { opacity: .1 }, { opacity: 1 })
        .to('.news1', .8, { opacity: .1 })
        .repeat(100)
    // .to('.hand', 1.5, { x: 500, y: -180 });
});
const open = () => {

    const timeline = gsap.timeline();
    timeline.to('.hand', 1.66, { x: 300, y: -185, opacity: 1 })
        .call(() => {
            next.value = true;
        });
    const timeline1 = gsap.timeline();
    timeline1.fromTo('.btn', 0.7, { opacity: .6 }, { opacity: 1 })
        .to('.btn', 0.7, { opacity: .65 }).repeat(50);

}
const goto = () => {
    router.push('/schoolgate');

}

</script>
<style scoped lang='scss'>
.container {
    // position: absolute;
    width: 100%;
    height: 100%;
    // top: 0;
    backdrop-filter: blur(16px);
    animation: fade-in .45s ease;
}

.bgImg {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    z-index: -100;
}


.envelope {
    position: absolute;
    width: 40%;
    left: 20%;
    top: 5%;
    cursor: pointer;
}

.news1 {
    position: absolute;
    left: 42%;
    top: 15%;
    width: 6%;
    cursor: pointer;
}

.hand {
    position: absolute;
    width: 50%;
    left: 4%;
    top: 55%;
    opacity: 0;
}

.letter {
    position: absolute;
    z-index: 100;
    width: 70%;
    left: 10%;
    top: -2%;
}

.btn {
    position: absolute;
    z-index: 100;
    width: 15%;
    left: 80%;
    top: 75%;

    opacity: 0;
    cursor: pointer;

}

@keyframes fade-in {
    from {
        opacity: 0;
    }

    to {
        opacity: 1;
    }
}
</style>
<template>
    <div class="container">
        <!-- <h2>大学生防诈骗课堂</h2> -->
        <div class="form">
            <h4>登 录</h4>
            <el-form ref="formRef" :model="form" :rules="rules" style="padding: 0 20px">
                <el-form-item prop="username">
                    <el-input 
                    placeholder="账号"
                    v-model="form.username"
                    :prefix-icon="User"
                    clearable
                    />
                </el-form-item>
                <el-form-item prop="password" style="margin-bottom: 10px">
                    <el-input
                    placeholder="密码"
                    v-model="form.password"
                    type="password"
                    :prefix-icon="Lock"
                    show-password 
                    @keydown.enter="submit(formRef)"
                    />
                </el-form-item>
                <el-form-item style="margin-bottom: 10px;">
                    <el-row justify="center" style="width: 100%">
                        <el-radio-group v-model="identity">
                            <el-radio label="学生" />
                            <el-radio label="管理员" />
                        </el-radio-group>
                    </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" style="width: 100%;" @click="submit(formRef)">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script setup lang='ts'>
import { User, Lock } from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';
import { ref, reactive } from 'vue';
import { useLogin } from '../hooks';

import type { FormInstance, FormRules } from 'element-plus';

const formRef = ref<FormInstance>();
const identity = ref('学生');
const router = useRouter();
const rules = reactive<FormRules>({
    username: {
        required: true,
        trigger: 'change',
        message: '请输入账号'
    },
    password: {
        required: true,
        trigger: 'change',
        message: '请输入密码'
    },
});
const {
    username,
    password,
    login
} = useLogin(identity);
const form = reactive({
    username,
    password
});

const submit = async (formEl: FormInstance | undefined) => {
    if(!formEl) return;
    await formEl.validate(valid => valid ? login() : undefined);
}

</script>

<style scoped lang='scss'>
.container {
    position: absolute;
    width: 100%;
    height: 100%;
    background: url('../img/首页.png') no-repeat;
    background-size: cover;
}

.form {
    position: absolute;
    width: 400px;
    height: 390;
    left: 31%;
    top: 35%;
    border: solid 1px #DCDFE6;
    padding: 30px;
    border-radius: 15px;
    background-color: rgba(255, 255, 255, .8);
    backdrop-filter: blur(6px);
    box-shadow:
        0px 0px 2.2px rgba(0, 0, 0, 0.02),
        0px 0px 5.3px rgba(0, 0, 0, 0.028),
        0px 0px 10px rgba(0, 0, 0, 0.035),
        0px 0px 17.9px rgba(0, 0, 0, 0.042),
        0px 0px 33.4px rgba(0, 0, 0, 0.05),
        0px 0px 80px rgba(0, 0, 0, 0.07)
        ;

    //background-color: rgba(28, 130, 132, 0.226);

}

h2, h4 {
    color: #000;
   
}

h4 {
    font-size: 28px;
    margin-bottom: 20px;
    font-weight: 700;
}

h2 {
    color: #333;
    font-size: 52px;
    // background-color: rgba(255, 255, 255, .8);
    // backdrop-filter: blur(6px);
    // width: 600px;
    // padding: 20px 10px;
    // border-radius: 35px;
    // margin: 0 auto;
    // margin-top: 50px;
    // margin-left: 460px;
}

</style>
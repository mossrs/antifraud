import { createWebHistory } from 'vue-router';

const Login = () => import('../components/Login.vue');
const Home = () => import('../components/Home.vue');
const School = () => import('../components/Scene01/School.vue');
const SchoolGate = () => import('../components/Scene01/SchoolGate.vue');
const Admin = () => import('../components/admin/Index.vue');
const Users = () => import('../components/admin/Users.vue');
const Quiz = () => import('../components/admin/Quiz.vue');
const Setting = () => import('../components/admin/Setting.vue');

const fraud1 = () => import('../components/Scene02/review/fraud1.vue');
const fraud2 = () => import('../components/Scene02/review/fraud2.vue');
const fraud3 = () => import('../components/Scene02/review/fraud3.vue');
const fraud4 = () => import('../components/Scene02/review/fraud4.vue');
const fraud5 = () => import('../components/Scene02/review/fraud5.vue');
const s2Index = () => import('../components/Scene02/index.vue');
const Persona = () => import('../components/Scene02/PersonaPage.vue');

import type { RouterOptions } from 'vue-router';

const routerOptions: RouterOptions = {
    history: createWebHistory(),
    routes: [
        {
            name: 'login',
            path: '/',
            component: Login,
            meta: {
                index: 0
            }
        },
        {
            name: 'home',
            path: '/home',
            component: Home,
            meta: {
                index: 1
            }
        },
        {
            name: 'admin',
            path: '/admin',
            component: Admin,
            meta: {
                index: 1
            },
            children: [
                {
                    name: 'user',
                    path: '/admin/user',
                    component: Users,
                    meta: {
                        index: 1
                    }
                },
                {
                    name: 'quiz',
                    path: '/admin/quiz',
                    component: Quiz,
                    meta: {
                        index: 1
                    }
                },
                {
                    name: 'setting',
                    path: '/admin/setting',
                    component: Setting,
                    meta: {
                        index: 1
                    }
                }
            ]
        },
        {
            name: 'schoolgate',
            path: '/schoolgate',
            component: SchoolGate,
            meta: {
                index: 2
            }
        },
        {
            name: 'school',
            path: '/school',
            component: School,
            meta: {
                index: 3
            }
        },
        {
            name: 's2Index',
            path: '/s2Index',
            component: s2Index,
            meta: {
                index: 4
            }
        },
        {
            name: 'fraud1',
            path: '/fraud1',
            component: fraud1,
            meta: {
                index: 5
            }
        },
        {
            name: 'fraud2',
            path: '/fraud2',
            component: fraud2,
            meta: {
                index: 5
            }
        },
        {
            name: 'fraud3',
            path: '/fraud3',
            component: fraud3,
            meta: {
                index: 5
            }
        },
        {
            name: 'fraud4',
            path: '/fraud4',
            component: fraud4,
            meta: {
                index: 5
            }
        },
        {
            name: 'fraud5',
            path: '/fraud5',
            component: fraud5,
            meta: {
                index: 5
            }
        },
        {
            name: 'persona',
            path: '/persona',
            component: Persona,
            meta: {
                index: 5
            }
        }
    ]
};

export default routerOptions;
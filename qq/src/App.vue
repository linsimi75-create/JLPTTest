
<script setup lang="ts">
import { ref } from 'vue';


import { useRoute, useRouter } from 'vue-router';
const route = useRoute();
const router = useRouter();
const menu1Value = ref(route.path === '/exam' ? 'item2' : 'item1');

const changeHandler = (value: string) => {
    menu1Value.value = value;
    if (value === 'item1') {
        router.push('/');
    } else if (value === 'item2') {
        router.push('/exam');
    }
};



const handleClick = (label: string) => {
  console.log('Clicked on:', label);
};

const handleHover = (label: string) => {
  console.log('Hovered over:', label);
};




</script>

<template>

  <div class="GlobalView">

        <t-affix :offset-top="0" class="Global_Nav_Menu">
            <t-head-menu v-model:value="menu1Value" theme="light" @change="changeHandler">
                <template #logo>
                    <img height="28" src="https://tdesign.gtimg.com/site/baseLogo-light.png" alt="logo" />
                </template>
                <t-menu-item value="item1">
                    <RouterLink to="/">NHK放送</RouterLink>
                </t-menu-item>
                <t-menu-item value="item2"> 
                    <RouterLink to="/exam">刷题</RouterLink>
                </t-menu-item>
            </t-head-menu>
        </t-affix>
        <div class="Global_Content">
           <router-view></router-view>
        </div>

  </div>
 
</template>

<style scoped lang="less">

.GlobalView {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    background-color: #f5f5f5;

    .Global_Nav_Menu {
        width: 100%;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        z-index: 100;
    }
    .Global_Content {
        flex: 1;
        padding: 20px;
        box-sizing: border-box;
    }

   
}

</style>
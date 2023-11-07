var u1 = document.getElementById("ul2");
var l1 = document.getElementById("l1");
var l2 = document.getElementById("l2");

//添加事件控制显示隐藏
function change() {
    if (i == 0) {
//添加属性 hidden
        u1.setAttribute("hidden", "hidden");
//移除class on
        l1.classList.remove("on");
//添加class on
        l2.className = "on";
        i--;
    } else {
//移除属性 hidden
        u1.removeAttribute("hidden");
//移除属性class on
        l2.classList.remove("on");
//添加class on
        l1.className = "on";
        i = 0;
    }

}

//获取元素
var tt = document.getElementById('t1');
//添加鼠标移入事件
tt.onmouseover = function () {
    this.style.color = 'red';
};
//添加鼠标移出事件
tt.onmouseout = function () {
    this.style.color = 'black';

};
var t2 = document.getElementById('t2');
t2.onmouseover = function () {
    this.style.color = 'red';
};
t2.onmouseout = function () {
    this.style.color = 'black';

};
var t3 = document.getElementById('t3');
t3.onmouseover = function () {
    this.style.color = 'red';
};
t3.onmouseout = function () {
    this.style.color = 'black';

};
var t4 = document.getElementById('t4');
t4.onmouseover = function () {
    this.style.color = 'red';
};
t4.onmouseout = function () {
    this.style.color = 'black';

};
var t5 = document.getElementById('t5');
t5.onmouseover = function () {
    this.style.color = 'red';
};
t5.onmouseout = function () {
    this.style.color = 'black';

};
var t6 = document.getElementById('t6');
t6.onmouseover = function () {
    this.style.color = 'pink';
    this.style.fontSize = '16px';
};
t6.onmouseout = function () {
    this.style.color = 'black';
    this.style.fontSize = '18px';
};
var t7 = document.getElementById('t7');
t7.onmouseover = function () {
    this.style.color = 'pink';
    this.style.fontSize = '18px';
};
t7.onmouseout = function () {
    this.style.color = 'black';
    this.style.fontSize = '16px';
};
var t8 = document.getElementById('t8');
t8.onmouseover = function () {
    this.style.color = 'pink';
    this.style.fontSize = '18px';
};
t8.onmouseout = function () {
    this.style.color = 'black';
    this.style.fontSize = '16px';
};
var t9 = document.getElementById('t9');
t9.onmouseover = function () {
    this.style.color = 'pink';
    this.style.fontSize = '18px';
};
t9.onmouseout = function () {
    this.style.color = 'black';
    this.style.fontSize = '16px';

};

var l1 = document.getElementById('l1');
l1.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
l1.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};
var l2 = document.getElementById('l2');
l2.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
l2.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};
var l3 = document.getElementById('l3');
l3.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
l3.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};
var l4 = document.getElementById('l4');
l4.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
l4.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};

var uu1 = document.getElementById('uu1');
uu1.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
uu1.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};
var uu2 = document.getElementById('uu2');
uu2.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
uu2.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};
var uu3 = document.getElementById('uu3');
uu3.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
uu3.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};
var uu4 = document.getElementById('uu4');
uu4.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
uu4.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};
var uu5 = document.getElementById('uu5');
uu5.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
uu5.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};
var uu6 = document.getElementById('uu6');
uu6.onmouseover = function () {
    this.style.transform = 'scale(1.1)';
};
uu6.onmouseout = function () {
    this.style.transform = 'scale(1.0)';

};




document.addEventListener("DOMContentLoaded", function () {
    // 获取按钮和目标div的引用
    var b1 = document.getElementById("button-1");
    var b2 = document.getElementById("button-2");
    var div1 = document.getElementById("flower-package");
    var div2 = document.getElementById("flower");

    // 添加按钮点击事件监听器
    b1.addEventListener("click", function () {
        // 使用滚动行为实现平滑滚动
        div1.scrollIntoView({behavior: "smooth"});
    });
    b2.addEventListener("click", function () {
        // 使用滚动行为实现平滑滚动
        div2.scrollIntoView({behavior: "smooth"});
    });
});

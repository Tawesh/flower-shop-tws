new Vue({
    el: "#app",
    data: {
        visibleDialog: false,
        deadline3: Date.now() + 1000 * 60 * 5,
        showPayImg: false,
        formInfo: {
            addr: "",
            phone: "",
        },
        selectAll: false,
        radio: '0',
        products: [],
        flowerProducts: [],
        giftProducts: [],
        payPrice: 0,
        orderId: "",
        buyCardIdList: [],
    },
    created() {
        this.init_data()
    },
    computed: {

        totalPrice: function () {
            this.payPrice = this.products.reduce((total, product) => {
                if (product.selected) {
                    return total + product.itemPrice * product.buyN;
                }
                return total;
            }, 0)
            return this.products.reduce((total, product) => {
                if (product.selected) {
                    return total + product.itemPrice * product.buyN;
                }
                return total;
            }, 0);
        }
    },
    methods: {
        hilarity() {
            this.$swal({
                title: "支付超时!",
                text: "亲！请在规定的时间内完成支付哦!",
                icon: "warning",
                button: "好的，我已知晓!",
            }).then(() => {
                this.showPayImg = false
            })
        },
        toggleAll: function () {
            this.products.forEach((product) => {
                product.selected = this.selectAll;
            });
        },
        updateTotal: function () {
            this.selectAll = this.products.every(product => product.selected);
        },
        increment: function (product) {
            product.buyN++;
        },
        decrement: function (product) {
            if (product.buyN > 1) {
                product.buyN--;
            }
        },
        remove: function (product) {
            // 此处应该发送请求到后端来删除商品
            this.$confirm("您确定要从购物车中移除该商品吗？", "系统提示", {
                confirmButtonText: "是的",
                cancelButtonText: "没有，我点错了",
                center: true,
                type: "warning",
                showClose: false,
                closeOnPressEscape: false,
                closeOnClickModal: false,
            }).then(() => {
                this.removeFromBackend(product.cartId);
                // this.init_data()
            })
        },
        init_data() {
            axios.post("/shop/cart/info?userId=" + localStorage.getItem("fs-userId")).then(response => {
                if (response.data.code === 0) {
                    const listTemp1 = response.data.response.data["0"]
                    this.flowerProducts = listTemp1.map(item => ({
                        ...item, // 保留原有属性
                        selected: item.selected === "true", // 将 "selected" 转换为布尔值
                    }));
                    const listTemp2 = response.data.response.data["1"]
                    this.giftProducts = listTemp2.map(item => ({
                        ...item, // 保留原有属性
                        selected: item.selected === "true", // 将 "selected" 转换为布尔值
                    }));
                }
            }).catch(() => {
                this.$message.error("系统异常，请稍后再试")
            })
        },
        removeFromBackend: function (productId) {
            // 在这里发送请求到后端来删除商品
            // 假设你使用了Axios库，你可以这样发送DELETE请求
            axios.post("/shop/cart/delete?cartId=" + productId)
                .then(response => {
                    // 成功从后端删除商品后，执行以下操作
                    if (response.data.code === 0) {
                        // const index = this.products.findIndex(product => product.id === productId);
                        // if (index !== -1) {
                        //     this.products.splice(index, 1);
                        // }
                        this.init_data()
                    } else {
                        this.$message.error("移除失败！请稍后再试")
                    }

                })
                .catch(error => {
                    console.error("删除商品时出错：", error);
                    this.$message.error("系统异常！请稍后再试")
                });
        },
        getOrder() {
            if (this.payPrice > 0) {
                this.visibleDialog = true

            } else {
                this.$message.warning("请先选择商品")
            }

        },
        pay() {
            this.showPayImg = true
        },
        cancelPay(index) {
            if (index === 0) {
                this.visibleDialog = false
            }
            if (index === 1) {
                this.showPayImg = false
                this.$message.warning("支付不成功，购买失败")
            }

        },
        init_Pay() {
            this.showPayImg = false
            this.rePay(true, 1, this.orderId)
        },
        init_order() {
            if (this.formInfo.addr !== "" && this.formInfo.phone !== "") {
                this.visibleDialog = false
                this.addOrder(0)
                setTimeout(() => {
                    this.pay()
                }, 1000)
            } else {
                this.$message.warning("请正确填写您的收货信息")
            }
        },
        //添加订单信息
        addOrder(payStatus) {
            this.orderId = this.generateOrderNumber()
            const info = this.getSelectedProductInfo()
            //封装订单信息
            const order = {
                "orderId": this.orderId,
                "itemTitle": info.title,
                "userId": localStorage.getItem("fs-userId"),
                "price": info.allPay,
                "number": info.number,
                "itemId": info.id,
                "total": info.allPay,
                "payStatus": payStatus,
                "orderStatus": 0,
                "address": this.formInfo.addr,
                "phone": this.formInfo.phone,
                "changeDate": this.getCurrentTime(),
            }
            console.log(order)
            axios.post("/web/tws/fs/api/data/order-info/add?tk=" + localStorage.getItem("userToken"), order).then(response => {
                if (response.data.code === 0) {
                    this.$notify({
                        title: '成功',
                        message: '订单已生成',
                        type: 'success'
                    });
                } else if (response.data.code === -2) {
                    this.$notify({
                        title: '警告',
                        message: '请先登录',
                        type: 'warning'
                    });
                    setTimeout(() => {
                        const nowDate = new Date()
                        window.location = "/user/login-register?source=buy&t=" + nowDate.getTime()
                    }, 2000)
                } else {
                    this.$notify({
                        title: '失败',
                        message: '订单未生成',
                        type: 'error'
                    });
                }
            }).catch(() => {
                this.$message.error("系统异常，请稍后重试")
            })
        },
        generateOrderNumber() {
            // 获取当前日期，格式为YYYYMMDD
            const date = new Date();
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要+1
            const day = String(date.getDate()).padStart(2, '0');

            // 生成一个四位数的随机数
            const random = String(Math.floor(Math.random() * 10000)).padStart(4, '0');

            // 组合订单号
            return `${year}${month}${day}${random}`;
        },
        getCurrentTime() {
            const date = new Date();

            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要+1
            const day = String(date.getDate()).padStart(2, '0');
            const hours = String(date.getHours()).padStart(2, '0');
            const minutes = String(date.getMinutes()).padStart(2, '0');
            const seconds = String(date.getSeconds()).padStart(2, '0');

            return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
        },
        getSelectedProductInfo: function () {
            const selectedProducts = this.products.filter(product => product.selected);

            const selectedProductInfo = {}

            selectedProducts.forEach(product => {
                this.buyCardIdList.push(product.cartId)
                if (!selectedProductInfo[product.cartId]) {
                    selectedProductInfo[product.cartId] = {
                        id: product.itemId,
                        title: product.itemTitle, // 商品标题
                        number: product.buyN,
                        price: product.itemPrice
                    };
                }
            });
            const selectedProductInfos = {
                id: "",   // 初始为空字符串
                title: "", // 初始为空字符串
                allPay: 0,
                number: 0,
            };

            const data = Object.values(selectedProductInfo)
            console.log(data)
            data.forEach(product => {
                // 如果不是第一个选中的商品，添加空格
                selectedProductInfos.id += " ";
                selectedProductInfos.title += " ";
                selectedProductInfos.allPay += product.price * product.number
                selectedProductInfos.number += product.number

                // 将商品信息添加到字符串中
                selectedProductInfos.id += product.id;
                selectedProductInfos.title += product.title;
            });

            return selectedProductInfos;
        },
        rePay(isPay, payNum, orderId) {
            if (isPay) {
                axios.post("/web/tws/fs/api/data/repay", {
                    "payNum": payNum,
                    "orderId": orderId
                }).then(response => {
                    if (response.data.code === 0) {
                        this.$swal({
                            title: "感谢您的信赖!",
                            text: "亲！等待3-4个工作日即可收货哦!",
                            icon: "success",
                            button: "好的，我已知晓!",
                        }).then(() => {
                            axios.post("/shop/cart/clear-buy", this.buyCardIdList).then(resp => {
                                if (resp.data.code === 0) {
                                    this.init_data()
                                }
                            }).catch(() => {
                                this.$message.error("系统异常，请稍后再试")
                            })
                        })
                    } else {
                        this.$message.error("支付失败")
                    }
                })

            }
        },
        clear_cart() {
            //购买成功后清空购物车

        }
    }
});

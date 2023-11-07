!function (t, e) {
    "object" == typeof exports && "object" == typeof module ? module.exports = e() : "function" == typeof define && define.amd ? define("VueLoading", [], e) : "object" == typeof exports ? exports.VueLoading = e() : t.VueLoading = e()
}(this, (() => (() => {
    "use strict";
    var t = {
        d: (e, i) => {
            for (var n in i) t.o(i, n) && !t.o(e, n) && Object.defineProperty(e, n, {enumerable: !0, get: i[n]})
        }, o: (t, e) => Object.prototype.hasOwnProperty.call(t, e)
    }, e = {};
    t.d(e, {default: () => c});
    const i = "undefined" != typeof window ? window.HTMLElement : Object, n = {
        mounted() {
            this.enforceFocus && document.addEventListener("focusin", this.focusIn)
        }, methods: {
            focusIn(t) {
                if (!this.isActive) return;
                if (t.target === this.$el || this.$el.contains(t.target)) return;
                let e = this.container ? this.container : this.isFullPage ? null : this.$el.parentElement;
                (this.isFullPage || e && e.contains(t.target)) && (t.preventDefault(), this.$el.focus())
            }
        }, beforeDestroy() {
            document.removeEventListener("focusin", this.focusIn)
        }
    };

    function a(t, e, i, n, a, r, o, s) {
        var l, u = "function" == typeof t ? t.options : t;
        if (e && (u.render = e, u.staticRenderFns = i, u._compiled = !0), n && (u.functional = !0), r && (u._scopeId = "data-v-" + r), o ? (l = function (t) {
            (t = t || this.$vnode && this.$vnode.ssrContext || this.parent && this.parent.$vnode && this.parent.$vnode.ssrContext) || "undefined" == typeof __VUE_SSR_CONTEXT__ || (t = __VUE_SSR_CONTEXT__), a && a.call(this, t), t && t._registeredComponents && t._registeredComponents.add(o)
        }, u._ssrRegister = l) : a && (l = s ? function () {
            a.call(this, (u.functional ? this.parent : this).$root.$options.shadowRoot)
        } : a), l) if (u.functional) {
            u._injectStyles = l;
            var c = u.render;
            u.render = function (t, e) {
                return l.call(e), c(t, e)
            }
        } else {
            var d = u.beforeCreate;
            u.beforeCreate = d ? [].concat(d, l) : [l]
        }
        return {exports: t, options: u}
    }

    const r = a({
        name: "spinner",
        props: {
            color: {type: String, default: "#000"},
            height: {type: Number, default: 64},
            width: {type: Number, default: 64}
        }
    }, (function () {
        var t = this, e = t._self._c;
        return e("svg", {
            attrs: {
                viewBox: "0 0 38 38",
                xmlns: "http://www.w3.org/2000/svg",
                width: t.width,
                height: t.height,
                stroke: t.color
            }
        }, [e("g", {attrs: {fill: "none", "fill-rule": "evenodd"}}, [e("g", {
            attrs: {
                transform: "translate(1 1)",
                "stroke-width": "2"
            }
        }, [e("circle", {
            attrs: {
                "stroke-opacity": ".25",
                cx: "18",
                cy: "18",
                r: "18"
            }
        }), e("path", {attrs: {d: "M36 18c0-9.94-8.06-18-18-18"}}, [e("animateTransform", {
            attrs: {
                attributeName: "transform",
                type: "rotate",
                from: "0 18 18",
                to: "360 18 18",
                dur: "0.8s",
                repeatCount: "indefinite"
            }
        })], 1)])])])
    }), [], !1, null, null, null).exports;
    const o = a({
        name: "dots",
        props: {
            color: {type: String, default: "#000"},
            height: {type: Number, default: 240},
            width: {type: Number, default: 60}
        }
    }, (function () {
        var t = this, e = t._self._c;
        return e("svg", {
            attrs: {
                viewBox: "0 0 120 30",
                xmlns: "http://www.w3.org/2000/svg",
                fill: t.color,
                width: t.width,
                height: t.height
            }
        }, [e("circle", {attrs: {cx: "15", cy: "15", r: "15"}}, [e("animate", {
            attrs: {
                attributeName: "r",
                from: "15",
                to: "15",
                begin: "0s",
                dur: "0.8s",
                values: "15;9;15",
                calcMode: "linear",
                repeatCount: "indefinite"
            }
        }), e("animate", {
            attrs: {
                attributeName: "fill-opacity",
                from: "1",
                to: "1",
                begin: "0s",
                dur: "0.8s",
                values: "1;.5;1",
                calcMode: "linear",
                repeatCount: "indefinite"
            }
        })]), e("circle", {
            attrs: {
                cx: "60",
                cy: "15",
                r: "9",
                "fill-opacity": "0.3"
            }
        }, [e("animate", {
            attrs: {
                attributeName: "r",
                from: "9",
                to: "9",
                begin: "0s",
                dur: "0.8s",
                values: "9;15;9",
                calcMode: "linear",
                repeatCount: "indefinite"
            }
        }), e("animate", {
            attrs: {
                attributeName: "fill-opacity",
                from: "0.5",
                to: "0.5",
                begin: "0s",
                dur: "0.8s",
                values: ".5;1;.5",
                calcMode: "linear",
                repeatCount: "indefinite"
            }
        })]), e("circle", {attrs: {cx: "105", cy: "15", r: "15"}}, [e("animate", {
            attrs: {
                attributeName: "r",
                from: "15",
                to: "15",
                begin: "0s",
                dur: "0.8s",
                values: "15;9;15",
                calcMode: "linear",
                repeatCount: "indefinite"
            }
        }), e("animate", {
            attrs: {
                attributeName: "fill-opacity",
                from: "1",
                to: "1",
                begin: "0s",
                dur: "0.8s",
                values: "1;.5;1",
                calcMode: "linear",
                repeatCount: "indefinite"
            }
        })])])
    }), [], !1, null, null, null).exports;
    const s = a({
        name: "bars",
        props: {
            color: {type: String, default: "#000"},
            height: {type: Number, default: 40},
            width: {type: Number, default: 40}
        }
    }, (function () {
        var t = this, e = t._self._c;
        return e("svg", {
            attrs: {
                xmlns: "http://www.w3.org/2000/svg",
                viewBox: "0 0 30 30",
                height: t.height,
                width: t.width,
                fill: t.color
            }
        }, [e("rect", {
            attrs: {
                x: "0",
                y: "13",
                width: "4",
                height: "5"
            }
        }, [e("animate", {
            attrs: {
                attributeName: "height",
                attributeType: "XML",
                values: "5;21;5",
                begin: "0s",
                dur: "0.6s",
                repeatCount: "indefinite"
            }
        }), e("animate", {
            attrs: {
                attributeName: "y",
                attributeType: "XML",
                values: "13; 5; 13",
                begin: "0s",
                dur: "0.6s",
                repeatCount: "indefinite"
            }
        })]), e("rect", {
            attrs: {
                x: "10",
                y: "13",
                width: "4",
                height: "5"
            }
        }, [e("animate", {
            attrs: {
                attributeName: "height",
                attributeType: "XML",
                values: "5;21;5",
                begin: "0.15s",
                dur: "0.6s",
                repeatCount: "indefinite"
            }
        }), e("animate", {
            attrs: {
                attributeName: "y",
                attributeType: "XML",
                values: "13; 5; 13",
                begin: "0.15s",
                dur: "0.6s",
                repeatCount: "indefinite"
            }
        })]), e("rect", {
            attrs: {
                x: "20",
                y: "13",
                width: "4",
                height: "5"
            }
        }, [e("animate", {
            attrs: {
                attributeName: "height",
                attributeType: "XML",
                values: "5;21;5",
                begin: "0.3s",
                dur: "0.6s",
                repeatCount: "indefinite"
            }
        }), e("animate", {
            attrs: {
                attributeName: "y",
                attributeType: "XML",
                values: "13; 5; 13",
                begin: "0.3s",
                dur: "0.6s",
                repeatCount: "indefinite"
            }
        })])])
    }), [], !1, null, null, null).exports;
    const l = a({
        name: "vue-loading",
        mixins: [n],
        props: {
            active: Boolean,
            programmatic: Boolean,
            container: [Object, Function, i],
            isFullPage: {type: Boolean, default: !0},
            enforceFocus: {type: Boolean, default: !0},
            lockScroll: {type: Boolean, default: !1},
            transition: {type: String, default: "fade"},
            canCancel: Boolean,
            onCancel: {
                type: Function, default: () => {
                }
            },
            color: String,
            backgroundColor: String,
            blur: {type: String, default: "2px"},
            opacity: Number,
            width: Number,
            height: Number,
            zIndex: Number,
            loader: {type: String, default: "spinner"}
        },
        data() {
            return {isActive: this.active}
        },
        components: {Spinner: r, Dots: o, Bars: s},
        beforeMount() {
            this.programmatic && (this.container ? (this.isFullPage = !1, this.container.appendChild(this.$el)) : document.body.appendChild(this.$el))
        },
        mounted() {
            this.programmatic && (this.isActive = !0), document.addEventListener("keyup", this.keyPress)
        },
        methods: {
            cancel() {
                this.canCancel && this.isActive && (this.hide(), this.onCancel.apply(null, arguments))
            }, hide() {
                this.$emit("hide"), this.$emit("update:active", !1), this.programmatic && (this.isActive = !1, setTimeout((() => {
                    var t;
                    this.$destroy(), void 0 !== (t = this.$el).remove ? t.remove() : t.parentNode.removeChild(t)
                }), 150))
            }, disableScroll() {
                this.isFullPage && this.lockScroll && document.body.classList.add("vld-shown")
            }, enableScroll() {
                this.isFullPage && this.lockScroll && document.body.classList.remove("vld-shown")
            }, keyPress(t) {
                27 === t.keyCode && this.cancel()
            }
        },
        watch: {
            active(t) {
                this.isActive = t
            }, isActive(t) {
                t ? this.disableScroll() : this.enableScroll()
            }
        },
        computed: {
            bgStyle() {
                return {background: this.backgroundColor, opacity: this.opacity, backdropFilter: `blur(${this.blur})`}
            }
        },
        beforeDestroy() {
            document.removeEventListener("keyup", this.keyPress)
        }
    }, (function () {
        var t = this, e = t._self._c;
        return e("transition", {attrs: {name: t.transition}}, [e("div", {
            directives: [{
                name: "show",
                rawName: "v-show",
                value: t.isActive,
                expression: "isActive"
            }],
            staticClass: "vld-overlay is-active",
            class: {"is-full-page": t.isFullPage},
            style: {zIndex: t.zIndex},
            attrs: {tabindex: "0", "aria-busy": t.isActive, "aria-label": "Loading"}
        }, [e("div", {
            staticClass: "vld-background", style: t.bgStyle, on: {
                click: function (e) {
                    return e.preventDefault(), t.cancel.apply(null, arguments)
                }
            }
        }), e("div", {staticClass: "vld-icon"}, [t._t("before"), t._t("default", (function () {
            return [e(t.loader, {tag: "component", attrs: {color: t.color, width: t.width, height: t.height}})]
        })), t._t("after")], 2)])])
    }), [], !1, null, null, null).exports, u = function (t) {
        let e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {},
            i = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
        return {
            show() {
                let n = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : e,
                    a = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : i;
                const r = Object.assign({}, e, n, {programmatic: !0}),
                    o = new (t.extend(l))({el: document.createElement("div"), propsData: r}),
                    s = Object.assign({}, i, a);
                return Object.keys(s).map((t => {
                    o.$slots[t] = s[t]
                })), o
            }
        }
    };
    l.install = function (t) {
        let e = u(t, arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {}, arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {});
        t.$loading = e, t.prototype.$loading = e
    };
    const c = l;
    return e = e.default
})()));
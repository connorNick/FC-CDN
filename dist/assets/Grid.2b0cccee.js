import{f as t,a7 as g,ah as h,e as b,t as d,aF as f,W as j,z as x,aE as $,aH as w,co as i,ak as k,ag as v,m as l,bv as C,af as p,bc as y,v as I,o as M,c2 as S}from"./index.ba7b76b6.js";import{b as z}from"./Folder.2b4f545e.js";import{a as E,M as O}from"./Layout.6b158c8d.js";import{c as P}from"./useUtil.7112ed91.js";import{n as A}from"./index.75dece05.js";import{I as G}from"./ImageWithError.bccd861a.js";import{g as H,O as L}from"./icon.a2dac563.js";import"./index.771f7e4d.js";import"./api.819f16ad.js";import"./Markdown.f1d91bf9.js";import"./index.e2874e96.js";import"./FolderTree.e43fa167.js";const W=e=>{const{isHide:a}=P();if(a(e.obj))return null;const{setPathAs:s}=E(),o=t(h,{get color(){return g()},boxSize:"$12",get as(){return H(e.obj)}}),[c,n]=b(!1),u=d(()=>f()&&(c()||e.obj.selected)),{show:m}=z({id:1});return t(O.div,{initial:{opacity:0,scale:.9},animate:{opacity:1,scale:1},transition:{duration:.2},style:{width:"100%"},get children(){return t(j,{class:"grid-item",w:"$full",p:"$1",spacing:"$1",rounded:"$lg",transition:"all 0.3s",get _hover(){return{transform:"scale(1.06)",bgColor:x()}},as:A,get href(){return e.obj.name},onMouseEnter:()=>{n(!0),s(e.obj.name,e.obj.is_dir,!0)},onMouseLeave:()=>{n(!1)},onContextMenu:r=>{$(()=>{w(!1),i(e.index,!0,!0)}),m(r,{props:e.obj})},get children(){return[t(k,{class:"item-thumbnail",h:"70px",w:"$full","on:click":r=>{e.obj.type===L.IMAGE&&(r.stopPropagation(),r.preventDefault(),v.emit("gallery",e.obj.name))},pos:"relative",get children(){return[t(l,{get when(){return u()},get children(){return t(C,{pos:"absolute",left:"$1",top:"$1","on:click":r=>{r.stopPropagation()},get checked(){return e.obj.selected},onChange:r=>{i(e.index,r.target.checked)}})}}),t(l,{get when(){return e.obj.thumb},fallback:o,get children(){return t(G,{maxH:"$full",maxW:"$full",rounded:"$lg",shadow:"$md",get fallback(){return t(p,{size:"lg"})},fallbackErr:o,get src(){return e.obj.thumb},loading:"lazy"})}})]}}),t(y,{css:{whiteSpace:"nowrap",textOverflow:"ellipsis"},w:"$full",overflow:"hidden",textAlign:"center",fontSize:"$sm",get title(){return e.obj.name},get children(){return e.obj.name}})]}})}})},R=()=>t(S,{w:"$full",gap:"$1",templateColumns:"repeat(auto-fill, minmax(100px,1fr))",get children(){return t(I,{get each(){return M.objs},children:(e,a)=>t(W,{obj:e,get index(){return a()}})})}});export{R as default};

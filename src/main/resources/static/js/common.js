//注销
function logout(){
    if(confirm("确认注销？")){
        jQuery.ajax({
            url:"/blog/logout",
            type:"POST",
            data:{},
            success:function(result){
                if(result!=null && result.code==200){
                    location.href="/login.html";
                }else{
                    alert("退出登录失败，请稍后重试！");
                }
            }
        });
    }
}

function getUrlValue(key){
    var parma=location.search.substring(1);
    const parmas=parma.split("&");
    for(var i=0;i<parmas.length;i++){
        const item=parmas[i].split("=");
        if(item[0]==key){
            return item[1];
        }
    }
    return null;
}

var last=0;
function lastpage(){
    jQuery.ajax({
        url:"/blog/lastnum",
        type:"GET",
        data:{},
        async:false,
        success:function(result){
            if(result!=null&&result.code==200){
                last=result.data;
                return ;
            }else{
                alert("非法访问！");
                last=0;
                return ;
            }
        }
    });
}

// 表明是第几页
var start=-1;

function ToPage(i){
    console.log(last);
    if(i==0&&start==0){
        alert("已处于第一页!");
        return ;
    }
    if(i==-1 && start==0){
        alert("已处于第一页！");
        return ;
    }
    if(i==1 && start>=last){
        alert("已处于最后一页！");
        return ;
    }
    if(i==2 && start>=last){
        alert("已处于最后一页！");
        return ;
    }
    if(i==0){
        lastpage();
        start=0;
        console.log(last);
    }else if(i==1){
        start+=1;
    }else if(i==-1){
        start-=1;
    }else if(i==2){
        start=last;
    }

    jQuery.ajax({
        url:"/blog/allart",
        type:"GET",
        data:{"start":start},
        success:function(result){
            if(result!=null&&result.code==200){
                if(result.data!=null&&result.data.length>0&&result.data.length<=5){
                    var artListDiv ="";
                    for(var i=0;i<result.data.length;i++){
                        var artItem =result.data[i];
                        artListDiv += '<div class="blog">';
                        artListDiv += ' <div class="title">'+artItem.title+'</div>';
                        artListDiv += '<div class="date">'+artItem.updatetime+'</div>';
                        artListDiv += '<div class="desc">';
                        artListDiv += artItem.content;
                        artListDiv += '</div>';
                        artListDiv += ' <a href="blog_content.html?id='+artItem.id+'" class="detail">查看全文 &gt;&gt;</a>&nbsp;&nbsp;';
                        artListDiv += '</div>';
                    }
                    jQuery('#artDiv').html(artListDiv);
                }else if(result.data!=null&&result.data.length==0){
                    //网站不具有文章
                    jQuery("#artDiv").html("<h3>暂无文章</h3>");
                }
            }else{
                alert("查询文章列表出错！请重试！");
                return ;
            }
        }
    });
}

function Topage(i){
    console.log(last);
    if(i==0&&start==0){
        alert("已处于第一页!");
        return ;
    }
    if(i==-1 && start==0){
        alert("已处于第一页！");
        return ;
    }
    if(i==1 && start>=last){
        alert("已处于最后一页！");
        return ;
    }
    if(i==2 && start>=last){
        alert("已处于最后一页！");
        return ;
    }
    if(i==0){
        lastpage();
        start=0;
        console.log(last);
    }else if(i==1){
        start+=1;
    }else if(i==-1){
        start-=1;
    }else if(i==2){
        start=last;
    }

    jQuery.ajax({
        url:"/blog/allart",
        type:"GET",
        data:{"start":start},
        success:function(result){
            if(result!=null&&result.code==200){
                if(result.data!=null&&result.data.length>0&&result.data.length<=5){
                    var artListDiv ="";
                    for(var i=0;i<result.data.length;i++){
                        var artItem =result.data[i];
                        artListDiv += '<div class="blog">';
                        artListDiv += ' <div class="title">'+artItem.title+'</div>';
                        artListDiv += '<div class="date">'+artItem.updatetime+'</div>';
                        artListDiv += '<div class="desc">';
                        artListDiv += artItem.content;
                        artListDiv += '</div>';
                        artListDiv += ' <a href="myblog_content.html?id='+artItem.id+'" class="detail">查看全文 &gt;&gt;</a>&nbsp;&nbsp;';
                        artListDiv += '</div>';
                    }
                    jQuery('#artDiv').html(artListDiv);
                }else if(result.data!=null&&result.data.length==0){
                    //网站不具有文章
                    jQuery("#artDiv").html("<h3>暂无文章</h3>");
                }
            }else{
                alert("查询文章列表出错！请重试！");
                return ;
            }
        }
    });
}
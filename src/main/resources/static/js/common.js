function checkNotNull(value) {
    if (value === null || value === undefined) {
        throw new Error('Value cannot be null or undefined');
    }
    return value;
}

function zuiMeg(meg){
    new $.zui.Messager(meg, {
        type: 'warning' // 定义颜色主题
    }).show();
}

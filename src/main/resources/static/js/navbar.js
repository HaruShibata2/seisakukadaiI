// 現在のURLを取得
var currentPath = window.location.pathname;

// 各リンクのIDをチェックして、現在のページに対応するリンクに「active」クラスを追加
if (currentPath.includes('/profile')) {
    document.getElementById('profileLink').classList.add('active');
} else if (currentPath.includes('/setting')) {
    document.getElementById('settingLink').classList.add('active');
} else if (currentPath === '/') {
    document.getElementById('homeLink').classList.add('active');
}

# 概観

* `CookieManager`を使えばよさそう
   * `CookieManager cm = CookieManager.getInstance();`
* 主に使いそうなメソッド(CookieManager)
   * `CookieManager#setAcceptCookie`
   * `CookieManager#setAcceptCookie`
* 関連しそうなメソッドとか
   * webview自体のjavascript有効無効
      * webView.getSettings().setJavaScriptEnabled
   * webview内の遷移を自view内で
      * webView.setWebViewClient(new MyWebViewClient());

## 分かったこと

* cookie自体の基本的な利用方法
   * CookieManagerを使う
   * 永続化 := CookieManager#flush
      * そうしないとアプリが死んだらcookieは消える

## 分かってないこと

* 保存済みcookieの細かい制御(追加/削除)
   * keyにURL指定しながらやるとは思うが、cookieの中身が具体的にどんなものか知っておく必要があると思われる


## 参考URL

* https://re-engines.com/2019/03/27/android-webview-tips-2/


# アプリの内容

* 2つのActivityで構成
   * 最初に起動するActivity
      * [W/]ボタン
         * cookie有効でgoolge.comにアクセスする
         * Activity下半分のwebviewに表示する
      * [W/O]ボタン
         * cookie無効でgoolge.comにアクセスする
         * Activity下半分のwebviewに表示する
      * [FLUSH]ボタン
         * 現在取得済みcookieを永続化する
         * このボタンで永続化しないとアプリ終了にてcookieが消える
      * [CLEAR]ボタン
         * 保存済みのすべてのcookieを削除する
      * [DUMP]ボタン
         * 保存済みのcookieの中身をTextEditとLogCatにダンプ
      * [SWITCH]ボタン
         * 2つ目のActivityに切り替える
   * 2つ目のActivity
      * [BROWSE W/ COOKIE]ボタン
         * cookie有効でgoolge.comにアクセスする
      * [BACK TO 1ST]ボタン
         * 最初に起動するActivityを表示する

# 効果確認の手順

* 効果確認:cookie有効
   * 1つめのActivityで[W/]ボタンし、googleアカウントにログインする
   * [SWITCH]ボタンでActivityを切り替える
   * [BROWSE W/ COOKIE]ボタン
      => googleがログイン状態になっている
 
* 効果確認:cookie無効
   * cookie有効でログインした状態で、[W/O]ボタン
      => ログインしてない状態のgoogle.comが表示される

* 効果確認:cookie永続化
   * [CLEAR]ボタンを押すか、一度も[FLUSH]していない状態で、
   * [W/]によりgoolgeアカウントにログインする
   * アプリを終了させて再起動する
      => [W/]でブラウズしてもgoogleログインが切れている
   * 再度[W/]によりgoolgeアカウントにログインする
   * [FLUSH]ボタンを押す
   * アプリ再起動
   * [W/]によりgoolgeアカウントのログインが継続している


* その他:cookie削除
   * [CLEAR]ボタンを押すと、[W/ COOKIE]でもログインが切れる


# さらに見るなら

* cookieはURL別に管理されているので、URL指定で削除等できるはず。
   * https://stackoverflow.com/questions/37013763/how-should-i-test-cookies-for-webview-android

# ハマりポイント？

* http://ja.voidcc.com/question/p-frqyjlqc-dv.html

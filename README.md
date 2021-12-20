# rzandroid-java-sdk
Rz Android Java Git SDK

### GIT Command
```git_command
git init
git remote add origin https://github.com/rzrasel/rzandroid-java-sdk.git
git remote -v
git fetch && git checkout master
git add .
git commit -m "Add Readme & Git Commit File"
git pull
git push --all
git remote show origin
git status
git status

git push --delete origin tagname
git push --delete origin rzandroid-sdk-v-1.0.1
```

Git workflow
```GIT_WORKFLOW
1) Git branch "master"
    -
    - LogWriter and DebugLogger class created ⇒ Monday December 20, 2021, 17:24:25
    - ProInternet class created → Monday December 20, 2021, 17:03:01
    - ProInternet module created ⇨ Monday December 20, 2021, 16:59:51
    - Update gradel file and readme.md file ⇒ Monday December 20, 2021, 16:31:15
```

```PHP_DATE_TIME
echo date("D", (time() + 6 * 60 * 60)) . "day " . date("F j, Y, G:i:s", (time() + 6 * 60 * 60));
```
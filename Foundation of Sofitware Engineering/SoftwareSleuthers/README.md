# Software Sleuthers Group Project

Hello, World!


## Git workflow

A typical workflow for git is as follows: 
* Create a branch for a specific feature or issue
* Make changes and `commit` and `push` to this branch
* Keep this branch up to date with `master`, resolving conflicts if needed
* Create a PR (pull request) once ready to incorporate changes into master
* Have PR reviewed/checked by another team member
* When changes look good, merge the PR

### Clone the repo
To get started, you will need to clone the repo to your local machine. Example command using HTTPS: 

```shell
git clone https://github.com/KiliMcgee/SoftwareSleuthers.git
```

### Create a new branch
cd into the cloned repository and create a branch according to the naming convention `<your-name>-<functionality>` (e.g. logan-unit-tests). This can be done using cli command: 

```shell
git checkout -b <branch name> 
```

Or on the Github UI, you can create an issue, link a branch, then in cli do: 

```shell
git checkout master
git pull # pull new branches created on Github
git checkout <branch name> 
```

### Commit and push
To save aka make a commit for changes made on the branch: 
* stage the files you want to include in the commit 
* make a commit including a brief and ideally descriptive message
* push the changes from your local repo to the remote

Generally, smaller/more frequent commits are favored over larger, less frequent ones. 

example commands on cli:  
```shell
git add <path to file or directory> # repeat as needed until all desired things are staged
git commit -m "descriptive commit message"
git push
```
### Keeping up to date
It's good practice to keep your branch up to date with the other work that has been merged into master
* pull any new changes merged into master
* checkout your branch 
* merge changes from master into your branch 
* push the commit

```shell
git checkout master
git pull
git checkout <branch name>
git merge master
# fix any merge conflicts and re-add files
git push 
```

### Submitting an PR
On Github, create a pull request (PR) from your issue branch into master. Send a message in the Signal chat to let team members know the PR is ready for review. Once the code looks good to the reviewer and the PR has gotten at least one approval, the PR author can merge the PR into master. 

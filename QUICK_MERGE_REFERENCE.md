# Quick Reference: Merge Develop to Ujwal_playwrightAutomation

## Simple 4-Step Process

```bash
# Step 1: Switch to your branch
git checkout Ujwal_playwrightAutomation

# Step 2: Fetch latest changes from remote
git fetch origin

# Step 3: Merge develop into your branch
git merge origin/develop

# Step 4: Push your changes
git push origin Ujwal_playwrightAutomation
```

## If You Have Merge Conflicts

```bash
# 1. See which files have conflicts
git status

# 2. Open conflicting files and resolve them
#    Look for <<<<<<< HEAD, =======, and >>>>>>> markers
#    Edit the files to keep the code you want

# 3. Mark conflicts as resolved
git add .

# 4. Complete the merge
git commit -m "Merged develop branch into Ujwal_playwrightAutomation"

# 5. Push to remote
git push origin Ujwal_playwrightAutomation
```

## One-Liner (if no conflicts expected)

```bash
git checkout Ujwal_playwrightAutomation && git fetch origin && git merge origin/develop && git push origin Ujwal_playwrightAutomation
```

## Check Changes Before Merging

```bash
# See what commits will be merged
git log Ujwal_playwrightAutomation..origin/develop --oneline

# See what files will change
git diff Ujwal_playwrightAutomation...origin/develop --name-status
```

## Undo If Something Goes Wrong

```bash
# During merge (conflicts not resolved yet)
git merge --abort

# After merge commit (but before push)
git reset --hard HEAD~1
```

---

For detailed explanations and troubleshooting, see [BRANCH_MERGE_GUIDE.md](BRANCH_MERGE_GUIDE.md)

# How to Pull Changes from Develop Branch to Ujwal_playwrightAutomation Branch

This guide explains how to merge or pull changes from the `develop` branch into the `Ujwal_playwrightAutomation` branch.

## Prerequisites

- Git must be installed on your system
- You have cloned the repository locally
- You have the necessary permissions to push to the `Ujwal_playwrightAutomation` branch

## Option 1: Using Git Merge (Recommended for preserving history)

This approach merges the develop branch into your feature branch, creating a merge commit.

### Step 1: Ensure your local repository is up to date

```bash
# Fetch all remote branches
git fetch origin
```

### Step 2: Switch to the Ujwal_playwrightAutomation branch

```bash
# Switch to your target branch
git checkout Ujwal_playwrightAutomation
```

### Step 3: Pull the latest changes from your branch (if any)

```bash
# Pull latest changes from your branch
git pull origin Ujwal_playwrightAutomation
```

### Step 4: Merge develop branch into your branch

```bash
# Merge develop branch into Ujwal_playwrightAutomation
git merge origin/develop
```

### Step 5: Resolve any merge conflicts (if they occur)

If there are conflicts:

1. Git will notify you of conflicting files
2. Open each conflicting file and look for conflict markers:
   ```
   <<<<<<< HEAD
   Your changes
   =======
   Changes from develop
   >>>>>>> origin/develop
   ```
3. Edit the files to resolve conflicts
4. After resolving all conflicts:
   ```bash
   git add .
   git commit -m "Merged develop branch into Ujwal_playwrightAutomation"
   ```

### Step 6: Push the merged changes

```bash
# Push your updated branch to remote
git push origin Ujwal_playwrightAutomation
```

## Option 2: Using Git Rebase (For a cleaner history)

This approach replays your commits on top of the develop branch, creating a linear history.

⚠️ **Warning**: Only use rebase if you haven't shared your branch with others or if you're comfortable with rewriting history.

### Step 1: Ensure your local repository is up to date

```bash
# Fetch all remote branches
git fetch origin
```

### Step 2: Switch to the Ujwal_playwrightAutomation branch

```bash
# Switch to your target branch
git checkout Ujwal_playwrightAutomation
```

### Step 3: Pull the latest changes from your branch

```bash
# Pull latest changes from your branch
git pull origin Ujwal_playwrightAutomation
```

### Step 4: Rebase onto develop

```bash
# Rebase your branch onto develop
git rebase origin/develop
```

### Step 5: Resolve any conflicts (if they occur)

If there are conflicts during rebase:

1. Git will pause at the conflicting commit
2. Resolve conflicts in the affected files
3. After resolving conflicts:
   ```bash
   git add .
   git rebase --continue
   ```
4. Repeat until all conflicts are resolved

To abort the rebase if something goes wrong:
```bash
git rebase --abort
```

### Step 6: Force push the rebased branch

⚠️ **Warning**: Force push will overwrite the remote branch history!

```bash
# Force push with lease (safer than --force)
git push --force-with-lease origin Ujwal_playwrightAutomation
```

## Option 3: Pull Request Approach (Recommended for team environments)

If you want to review changes before merging:

1. Create a Pull Request on GitHub from `develop` to `Ujwal_playwrightAutomation`
2. Review the changes in the PR
3. Resolve any conflicts using GitHub's web interface or locally
4. Merge the PR when ready

## Quick Command Reference

### Complete merge workflow (one-liner):
```bash
git checkout Ujwal_playwrightAutomation && git pull origin Ujwal_playwrightAutomation && git merge origin/develop && git push origin Ujwal_playwrightAutomation
```

### Check what changes will be merged:
```bash
# View commits that will be merged
git log Ujwal_playwrightAutomation..develop

# View file changes that will be merged
git diff Ujwal_playwrightAutomation...develop
```

### Undo a merge (before pushing):
```bash
git merge --abort  # During an active merge
git reset --hard HEAD~1  # After merge commit (before push)
```

## Common Issues and Troubleshooting

### Issue 1: "Your local changes would be overwritten by merge"
**Solution**: Commit or stash your changes first
```bash
git stash
git merge origin/develop
git stash pop
```

### Issue 2: Branch doesn't exist
**Solution**: Fetch all branches and check available branches
```bash
git fetch origin
git branch -r  # List remote branches
```

### Issue 3: Permission denied when pushing
**Solution**: Ensure you have push access to the branch and your authentication is set up correctly
```bash
# Check remote URL
git remote -v

# Update remote if needed to use SSH
git remote set-url origin git@github.com:lanetix/QE_Playwright_Automation_Suite.git
```

### Issue 4: Too many conflicts
**Solution**: Consider creating a fresh branch from develop and manually porting your changes
```bash
git checkout develop
git pull origin develop
git checkout -b Ujwal_playwrightAutomation_new
# Manually copy your changes or cherry-pick specific commits
git cherry-pick <commit-hash>
```

## Best Practices

1. **Always fetch before merging**: Ensures you have the latest remote changes
2. **Commit your work first**: Make sure your working directory is clean before merging
3. **Test after merging**: Run tests to ensure the merge didn't break anything
4. **Communicate with team**: Let others know when you're merging major changes
5. **Use meaningful commit messages**: Helps track changes and understand history
6. **Backup important branches**: Create a backup branch before complex merges
   ```bash
   git checkout Ujwal_playwrightAutomation
   git branch Ujwal_playwrightAutomation_backup
   ```

## Summary

The most straightforward approach for pulling everything from `develop` to `Ujwal_playwrightAutomation` is:

```bash
# 1. Make sure you're on the right branch
git checkout Ujwal_playwrightAutomation

# 2. Fetch latest changes
git fetch origin

# 3. Merge develop into your branch
git merge origin/develop

# 4. Resolve any conflicts if needed, then push
git push origin Ujwal_playwrightAutomation
```

If you encounter any issues or need help with specific scenarios, refer to the troubleshooting section above.

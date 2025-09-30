# Git Branch Merge Workflow Visualization

## Visual Workflow: Merging Develop into Ujwal_playwrightAutomation

```
BEFORE MERGE:
=============

develop branch:          A---B---C---D---E
                                  \
Ujwal_playwrightAutomation:        F---G


AFTER MERGE:
============

develop branch:          A---B---C---D---E
                                  \         \
Ujwal_playwrightAutomation:        F---G---M
                                            ^
                                      (merge commit)
```

## Step-by-Step Visual Guide

### Step 1: Check Your Current Branch
```
$ git branch
  develop
* Ujwal_playwrightAutomation  ← You are here
```

### Step 2: Fetch Latest Changes
```
$ git fetch origin

Remote branches:
  ✓ develop (updated)
  ✓ Ujwal_playwrightAutomation (up to date)
```

### Step 3: Merge Develop
```
$ git merge origin/develop

Auto-merging files...
Merge made by the 'recursive' strategy.
```

### Step 4: Push Changes
```
$ git push origin Ujwal_playwrightAutomation

✓ Branch 'Ujwal_playwrightAutomation' updated
```

## Workflow Diagram with Commands

```
┌─────────────────────────────────────────────────────────────┐
│  START: You want to update Ujwal_playwrightAutomation       │
│         with latest changes from develop                     │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│  Step 1: Switch to target branch                            │
│  Command: git checkout Ujwal_playwrightAutomation           │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│  Step 2: Fetch latest changes                               │
│  Command: git fetch origin                                  │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│  Step 3: Merge develop into your branch                     │
│  Command: git merge origin/develop                          │
└─────────────────────────────────────────────────────────────┘
                            ↓
                    ┌───────┴───────┐
                    │               │
            ┌───────▼─────┐   ┌────▼────────┐
            │  Success!   │   │  Conflicts  │
            │  No Conflicts│   │  Detected   │
            └───────┬─────┘   └────┬────────┘
                    │               │
                    │       ┌───────▼────────────────────┐
                    │       │ Resolve conflicts manually  │
                    │       │ Then: git add .             │
                    │       │ Then: git commit            │
                    │       └───────┬────────────────────┘
                    │               │
                    └───────┬───────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│  Step 4: Push updated branch                                │
│  Command: git push origin Ujwal_playwrightAutomation        │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│  DONE! Your branch now has all changes from develop         │
└─────────────────────────────────────────────────────────────┘
```

## Understanding Merge Conflicts

When conflicts occur, Git will mark them in your files like this:

```
<<<<<<< HEAD (Your current changes in Ujwal_playwrightAutomation)
public void myMethod() {
    System.out.println("My implementation");
}
=======
public void myMethod() {
    System.out.println("Implementation from develop");
}
>>>>>>> origin/develop (Changes from develop branch)
```

**To resolve:**
1. Edit the file to keep the code you want
2. Remove the conflict markers (`<<<<<<<`, `=======`, `>>>>>>>`)
3. Save the file
4. Run `git add <filename>`
5. Run `git commit`

## Branch History Visualization

### Using Git Merge (creates merge commit)
```
Before:
    develop:     A---B---C
    feature:         D---E

After:
    develop:     A---B---C
    feature:         D---E---M
                         /
                    ----
```

### Using Git Rebase (linear history)
```
Before:
    develop:     A---B---C
    feature:         D---E

After:
    develop:     A---B---C
    feature:             D'--E'
```

## Common Branch States

### ✓ Up to date with develop
```
develop:          A---B---C
your-branch:      A---B---C---D
Status: ✓ Your branch includes all develop changes
```

### ⚠ Behind develop
```
develop:          A---B---C---D---E
your-branch:      A---B---C---F
Status: ⚠ Need to merge develop
```

### ⚠ Diverged from develop
```
develop:          A---B---C---D
                       \
your-branch:            E---F
Status: ⚠ Branches have different changes, merge needed
```

## Quick Decision Tree

```
Do you need to update your branch with develop changes?
│
├─ YES → Continue with merge process
│        └─ Are there conflicts?
│           ├─ NO  → Simple merge, done!
│           └─ YES → Resolve conflicts, then commit
│
└─ NO → Your branch is up to date!
```

## Tips for Successful Merging

1. **Always commit or stash your work first**
   ```
   git status  # Check for uncommitted changes
   git commit -am "Work in progress"  # OR
   git stash  # Save changes for later
   ```

2. **Preview changes before merging**
   ```
   git log Ujwal_playwrightAutomation..origin/develop --oneline
   git diff Ujwal_playwrightAutomation...origin/develop
   ```

3. **Create a backup branch (optional but safe)**
   ```
   git branch backup-ujwal-$(date +%Y%m%d)
   ```

4. **Test after merging**
   ```
   # Run your tests to ensure merge didn't break anything
   mvn clean test
   ```

---

For detailed step-by-step instructions, see [BRANCH_MERGE_GUIDE.md](BRANCH_MERGE_GUIDE.md)

For quick commands, see [QUICK_MERGE_REFERENCE.md](QUICK_MERGE_REFERENCE.md)

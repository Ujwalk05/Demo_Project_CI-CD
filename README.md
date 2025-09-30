# QE Playwright Automation Suite

This repository contains the Playwright-based automation test suite for quality engineering.

## Project Structure

- `src/main/java/com/winmore/` - Main source code
  - `actions/` - Action helper classes
  - `utils/` - Utility classes including PlaywrightDriver
- `src/test/java/com/winmore/` - Test source code
  - `hooks/` - Cucumber hooks
  - `pages/` - Page object models
  - `runner/` - Test runners
  - `stepdefinitions/` - Cucumber step definitions
- `src/test/resources/Features/` - Cucumber feature files

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Maven
- Playwright browsers installed

### Running Tests

```bash
# Run tests with Maven
mvn clean test

# Run with specific environment
mvn clean test -DsuiteXmlFile=napreprod.xml
mvn clean test -DsuiteXmlFile=qaprod.xml
```

## Git Workflow

### Working with Branches

If you need to merge changes from one branch to another, refer to these guides:

- **[Branch Merge Guide](BRANCH_MERGE_GUIDE.md)** - Comprehensive guide on merging branches
- **[Quick Merge Reference](QUICK_MERGE_REFERENCE.md)** - Quick command reference

### Common Scenarios

#### Merging develop into your feature branch

```bash
git checkout your-feature-branch
git fetch origin
git merge origin/develop
git push origin your-feature-branch
```

For detailed instructions, see [BRANCH_MERGE_GUIDE.md](BRANCH_MERGE_GUIDE.md).

## Test Configuration

Test environment configuration is managed through:
- `src/main/resources/config.properties` - Browser and environment settings
- `src/main/resources/objectRepository.properties` - Element locators
- XML files (`napreprod.xml`, `qaprod.xml`) - TestNG suite configuration

## Reports

After test execution, reports are generated in:
- `target/cucumber-reports/` - Cucumber HTML/JSON reports
- `Reports/` - Extent reports

## Contributing

1. Create a feature branch from `develop`
2. Make your changes
3. Test thoroughly
4. Create a pull request to merge back to `develop`

## Support

For questions or issues, please contact the QE team.

# Contributing

Thanks for your interest in contributing! Please follow these guidelines to help us review your PR quickly.

## Getting started

1. Install Java 11+ and Maven 3.8+
2. Clone the repo and build:
   ```bash
   mvn -B -ntp clean verify
   ```
3. Run tests:
   ```bash
   mvn -B -ntp test
   ```

## Coding standards

- Write clear, readable Java
- Keep methods focused and small
- Prefer meaningful names over abbreviations
- Add tests for new features and bug fixes

## Commit & PR guidelines

- Branch from `main`
- Keep commits atomic; use descriptive messages
- Ensure `mvn verify` passes locally
- Update `README.md` if you change usage or add features
- Include screenshots for GUI changes

## Reporting issues

Please include:
- Steps to reproduce
- Expected vs actual behavior
- Logs/errors and environment details

# Brasserie

## Dependencies
- [mysql-connector-j-9.2.0](https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-j-9.2.0.tar.gz)
Must be placed in the `lib` folder and renamed to `mysql-connector.jar`
- [flatlaf-3.5.4](https://repo1.maven.org/maven2/com/formdev/flatlaf/3.5.4/flatlaf-3.5.4.jar)
Must be placed in the `lib` folder and renamed to `flatlaf.jar`
- [flatlaf-extras-3.5.4](https://repo1.maven.org/maven2/com/formdev/flatlaf-extras/3.5.4/flatlaf-extras-3.5.4.jar)
Must be placed in the `lib` folder and renamed to `flatlaf-extras.jar`

## Environment
- Copy the `.env.example` file and rename it to `.env`

### Variables
- `DB_NAME` - Database name
- `DB_USERNAME` - Database user
- `DB_PASSWORD` - Database password
- `DB_HOST` - Database host
- `DB_PORT` - Database port

#### Macos exclusive
- `MACOS_MENU_BAR_ENABLED` - Show the menu bar in macOS instead of on the app display
# Brasserie

## Java
- Java 21 is required to run the application.

## Dependencies
- [mysql-connector-j-9.2.0](https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-j-9.2.0.tar.gz)
Must be placed in the `lib` folder and renamed to `mysql-connector.jar`
- [flatlaf-3.5.4](https://repo1.maven.org/maven2/com/formdev/flatlaf/3.5.4/flatlaf-3.5.4.jar)
Must be placed in the `lib` folder and renamed to `flatlaf.jar`

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


## Components
- `TableModelMaker` - Make a table model from a list of models (combined multiple models into one). The component can also open/close a model from the list.
- `SearchByLabelPanel` - Panel that contains a search bar and a list of items. The panel can be used to search for items in a list and display the results in a table. this component can handle any type.
- `NavbarPanel` - Panel that contains a navbar with buttons. The panel can be used to navigate between different pages in the app.
- `JNumberField` - A custom number field that only accepts numbers (INTEGER or FLOAT). float can have custom decimal places. Component can handle min and max values.
- `JEnhancedTextField` - A custom text field that can handle placeholders.
- `JDate` - A custom date field that can handle date formats. The component can handle min and max dates.
- `JEnhancedTable` - A responsive table.
  `JEnhancedTableScrollPanel` - A scrollable panel that contains a `JEnhancedTable`. Provides methods to update the table model and add context menus to the table rows.
- `JDualSliderPanel` - A custom dual slider that can handle min and max values. The component can handle min and max values.
- `GridBagLayoutHelper` - A helper class that simplifies the use of `GridBagLayout`.
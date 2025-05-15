# Brasserie

## Java
- Java 21 is required to run the application.

## Dependencies

- [mysql-connector-j-9.2.0](https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-j-9.2.0.tar.gz)
Must be placed in the `lib` folder and renamed to `mysql-connector.jar`
- [flatlaf-3.5.4](https://repo1.maven.org/maven2/com/formdev/flatlaf/3.5.4/flatlaf-3.5.4.jar)
Must be placed in the `lib` folder and renamed to `flatlaf.jar`

- [bcrypt](https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/jbcrypt-0.4.jar)
Must be placed in the `lib` folder and renamed to `bcrypt.jar`

## Environment
- Copy the `.env.example` file and rename it to `.env`

### Variables
- `DB_NAME` - Database name
- `DB_USERNAME` - Database user
- `DB_PASSWORD` - Database password
- `DB_HOST` - Database host
- `DB_PORT` - Database port



## Components
- `TableModelMaker` - Make a table model from a list of models (combined multiple models into one). The component can also open/close a model from the list.

![tableMaker_readme.png](resources%2FreadmeImages%2FtableMaker_readme.png)



- `SearchListPanel` - Panel that contains a search bar and a list of items. The panel can be used to search for items in a list and display the results in a table. this component can handle any type.

![searchByLabel_readme.png](resources%2FreadmeImages%2FsearchByLabel_readme.png)

- `NavbarPanel` - Panel that contains a navbar with buttons. The panel can be used to navigate between different pages in the app.

![navbar_readme.png](resources%2FreadmeImages%2Fnavbar_readme.png)


- `JEnhancedTextField` - A custom text field that can handle placeholders.

![jEnhancedTextField_readme.png](resources%2FreadmeImages%2FjEnhancedTextField_readme.png)

- `JDateField` - A derived class of `JEnhancedTextField` that can only handle dates. The component can also handle min and max dates.
- `JNumberField` - A derived class of `JEnhancedTextField` that can only handle numbers (INTEGER or FLOAT). float can have custom decimal places. Component can handle min and max values and disable negative values.
- `JEnhancedTable` - A responsive table. The table will automatically resize the columns to fit the content.

![jEnhancedTable_1_readme.png](resources%2FreadmeImages%2FjEnhancedTable_1_readme.png)
![jEnhancedTable_2_readme.png](resources%2FreadmeImages%2FjEnhancedTable_2_readme.png)

- `JEnhancedTableScrollPanel` - A scrollable panel that contains a `JEnhancedTable`. Provides methods to update the table model and add context menus to the table rows.

![jEnhancedTableScroll_readme.png](resources%2FreadmeImages%2FjEnhancedTableScroll_readme.png)

- `JDualSliderPanel` - A custom dual slider that can handle min and max values.

![jDualSlider_readme.png](resources%2FreadmeImages%2FjDualSlider_readme.png)

- `GridBagLayoutHelper` - A helper class that simplifies the use of `GridBagLayout`.

![gridBagLayoutHelper.png](resources%2FreadmeImages%2FgridBagLayoutHelper.png)

# Content
## Searches
### Search Item
Search an `item` in the database. The search is done by the `VAT code` of the `item`, the min/max `current quantity` and the min/max `price` of the `item`.

### Search Document

### Search Payment

## CRUD

### Process
- `Create` a new process
- `Read` all processes
- `Update` an existing process
- `Delete` a process

### Document
- `Create` a new document
- `Read` all documents
- `Update` an existing document
- `Delete` a document

## !!TACHE_METIER


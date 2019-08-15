# Gui-Commerce

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/logo.jpg)

  My very first web application. It is hosted by Heroku and uses the Postgres database (Similar to PostgreSQL). It covers user registration, login, CRUD and automated tests. Anyone can register and see a public page. Users can create, read, update and delete products.

## Check it out:

https://gui-commerce.herokuapp.com/

---


## Register

**1)** To register, on the **Register** button on the home page:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/register1.png)

---


**2)** Then write your name, username and password on the respective fields and click on **Create** butotn:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/register2.png)

*OBS: None of the fields can be empty.

---


**3)** If everything went fine, you should see the **Success** page. Click on **Home** button to proceed to login:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/success.png)

---


## Login

**1)** To login, click on the **login** button on the home page:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/login1.png)

---


**2)** Insert your username and password and click on **Login** button:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/login2.png)

---


## Create a product

**1)** To create and publish a product, set your product name, price and the link to it and hit the button **Add New Product**:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/create_product1.png)

---


**2)** If everything went fine, you should see the **Success** page. Click on **Home** button to proceed to home page and see your product:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/create_product2.png)

---


## Edit your product

**1)** To edit your product, click on the **Login** button and then on the **Edit** button on **Your published products** table:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/edit_product1.png)

---


**2)** Fill the fields with the updated information of your product and click on **Update Product** button:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/edit_product2.png)

---


**3)** If everything went fine, you should see the **Settings** page with your recently updated product:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/edit_product3.png)

---


## Delete user

**1)** If you want to delete your user and every associoated products with it, under your **Settings** page, scroll down and click the button **DELETE USER**:

![alt text](https://github.com/guidias1212/Gui-Commerce/blob/master/images/delete.png)

---


## For developers

Feel free to clone this repository and use it on your own. The file under:

```
/Gui-Commerce/src/main/java/DBConnection.java
```

Is responsible for connecting to a database.

Change the variables hostname, port, databasename, db_username and db_password to your databases credentials.

It assumes you have a **testing database** and a **production database**. To switch between them, just change the boolean variable **production** to **true** or **false**.
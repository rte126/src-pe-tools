-- define the root folder in which application folders are placed
-- YOU CAN CHANGE THIS IF NECESSARY
define ar = C:/dbclass

-- define the folder in which setup files are placed
-- YOU CAN CHANGE THIS IF NECESSARY
define sf = C:/dbclass

-- define name of current application, passed as parameter
define appnam = &1

-- define the name of the database schema for the app data 
define adbnam = &appnam.DB

-- define the location of the application root  
define a = &ar./_&appnam.App

-- define the location of the builder script
define ab = &a./build

-- define the location of the create script
define ac = &a./create

-- define the location of the app init script
define ai = &a./appinit

-- define the location of the app start script
define as = &a./appstart

-- define the location of the test script
define at = &a./test

prompt Setup Complete for &appnam

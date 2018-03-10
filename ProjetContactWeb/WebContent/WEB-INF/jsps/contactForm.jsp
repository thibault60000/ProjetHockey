<html>
    <head>
        <title>
            Contact
        </title>
    </head>
    <body>
        Ajoutons un contact : <br/>
        <form method="post" action="AddContactExecuteServlet">
            prénom :
            <input type="text" name="contact.prenom"/><br/>
            nom :
            <input type="text" name="contact.nom"/><br/>
            adresse :
            <input type="text" name="contact.adresse"/><br/>
            code postal :
            <input type="text" name="contact.codePostal"/><br/>
            ville :
            <input type="text" name="contact.ville"/><br/>
            téléphone portable :
            <input type="text" name="contact.mobile"/><br/>
            téléphone :
            <input type="text" name="contact.telephone"/><br/>
            <input type="submit"/>
        </form>
    </body>
</html>

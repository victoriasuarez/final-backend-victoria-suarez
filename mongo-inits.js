db.createUser(
    {
        user: "usrfinalmongo",
        pwd: "pwdfinalmongo",
        roles: [
            {
                role: "readWrite",
                db: "finalmongo"
            }
        ]
    }
);
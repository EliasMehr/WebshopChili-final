function handleLoginRequest(xhr, status, args) {
    if(args.validationFailed || !args.isSuccessfullyLoggedIn) {
        console.log("Failed login");
        PF('loginDialog').jq.effect("shake", {times:10}, 100);
    }
    else {
        console.log("Successful login");
        PF('loginDialog').hide();
        $('loginDialog').fadeOut();
    }
}
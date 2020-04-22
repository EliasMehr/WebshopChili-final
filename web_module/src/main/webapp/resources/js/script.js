// $(document).ready(function() {
//     rowExpansion(PF('customerData'));
// });

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

// function rowExpansion(customerData) {
//     let $this = customerData;
//     $this.tbody.children('tr').css('cursor', 'pointer')
//     $this.tbody.off('click.datatable-expansion', '> tr')
//         .on('click.datatable-expansion', '> tr', null, function() {
//             $this.toggleExpansion($(this).find('div.ui-row-toggler'));
//         });
// }
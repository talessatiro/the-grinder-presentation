angular
    .module('app')
    .controller('CustomerController', CustomerController);

CustomerController.$inject = ['$scope', '$location', '$routeParams', 'CustomerService'];

function CustomerController($scope, $location, $routeParams, CustomerService){
    var vm = this;
    vm.getCustomers = getCustomers;
    vm.getCustomer = getCustomer;
    vm.deleteCustomer = deleteCustomer;
    vm.createOrEditCustomer = createOrEditCustomer;

    /////////////////////////

    function getCustomer(customerId) {
        return CustomerService.getCustomer(customerId)
            .then(function(data){
                vm.customer = data.data;
            });
    }

    function getCustomers() {
        return CustomerService.getCustomers()
            .then(function(data) {
                vm.customers = data.data;
            });
    }

    function createOrEditCustomer() {
        if(vm.customer.id) {
            return CustomerService.update(vm.customer)
                .then(function(){
                    $("#myModal").modal('hide');
                    vm.customer = {};
                    getCustomers();
                });
        } else {
            return CustomerService.create(vm.customer)
                .then(function(){
                    $("#myModal").modal('hide');
                    vm.customer = {};
                    getCustomers();
                });
        }
    }

    function deleteCustomer(customerId) {
        return CustomerService.remove(customerId)
            .then(function(){
                getCustomers();
            });
    }

    function init() {
        getCustomers();
    }

    init();

}
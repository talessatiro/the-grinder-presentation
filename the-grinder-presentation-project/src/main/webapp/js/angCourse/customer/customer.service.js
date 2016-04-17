angular
    .module('app')
    .service('CustomerService', CustomerService);

CustomerService.$inject = ['$http'];

function CustomerService($http){

    var URL_BASE = '/api/v1/customers';

    var service = {
        getCustomers: getCustomers,
        getCustomer: getCustomer,
        create: create,
        update: update,
        remove: remove
    };

    return service;

    ////////////////////////////////

    function getCustomers() {
        return $http.get(URL_BASE);
    }

    function getCustomer(id) {
        return $http.get(URL_BASE + '/' + id);
    }

    function create(customer) {
        return $http.post(URL_BASE, customer);
    }

    function update(customer){
        return $http.put(URL_BASE, customer);
    }

    function remove(id){
        return $http.delete(URL_BASE + '/' + id);
    }
}
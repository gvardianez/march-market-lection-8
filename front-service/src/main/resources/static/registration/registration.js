angular.module('market').controller('registerController', function ($rootScope, $scope, $http) {
    $scope.newRegistration = function () {
        $http.post('http://localhost:5555/auth/registration', $scope.registerUserDto)
            .then(function (response) {
                $scope.registerUserDto = null;
            });
    }
});
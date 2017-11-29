(function (ng) {
    var mod = ng.module("empleadoModule", ['ui.router']);
    mod.constant("empleadosContext", "api/empleados");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/empleados/';
            var basePathArea = 'src/modules/areasdeconocimiento/';
            var basePathHoja = 'src/modules/hojasdevida/';
            var basePathOferta = 'src/modules/ofertas/';
            var basePathPropuesta = 'src/modules/propuestas/';
            $urlRouterProvider.otherwise("/empleadosList");
            
            $stateProvider.state('empleados', {
                url: '/empleados',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'empleados.html',
                        controller: 'empleadoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('empleadosList', {
                url: '/empleadoslist',
                parent: 'empleados',
                views: {
                    'listView': {
                        templateUrl: basePath + 'empleados.list.html'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['cliente', 'empleado']
                }
            }).state('empleadoAreas', {
                url: '/empleadoareas/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathArea + 'areasdeconocimiento.detailedList.html',
                        controller: 'areasDetailedCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('empleadoHojas', {
                url: '/empleadohojas/{empleadoId:int}/{empleadoHoja:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null,
                    empleadoHoja:null
                },
                views: {
                    'listView': {
                        templateUrl: basePathHoja + 'hojas.detail.html',
                        controller: 'hojasDetailedCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }

            }).state('empleadoOfertas', {
                url: '/empleadoOfertas/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathOferta + 'ofertas.detail.html',
                        controller: 'ofertasDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('empleadoOfertasCreate', {
                url: '/empleadoOfertas/{empleadoId:int}/crear',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathOferta + 'create/ofertas.create.html',
                        controller: 'ofertasNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('empleadoPropuestas', {
                url: '/empleadoPropuestas/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathPropuesta + 'propuestas.detail.html',
                        controller: 'propuestasDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('empleadoHojasCreate', {
                url: '/empleadohojas/{empleadoId:int}/crear',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {

                        templateUrl: basePathHoja + 'new/hojas.new.html',
                        controller: 'hojasNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('empleadoUpdate', {
                url: '/empleadoupdate/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/empleados.update.html',
                        controller: 'empleadoUpdateCtrl'   
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('empleadoDelete', {
                url: '/empleadodelete/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/empleados.delete.html',
                        controller: 'empleadoDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                } 
            }).state('empleadoCreate', {
                url: '/empleadocreate',
                parent: 'empleados',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/create/empleados.create.html',
                        controller: 'empleadoCreateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('areadeconocimientoDelete', {
                url: '/areadelete/{areadeconocimientoId:int}',
                parent: 'empleados',
                param: {
                    areadeconocimientoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathArea + '/delete/areasdeconocimiento.delete.html',
                        controller: 'areadeconocimientoDeleteCtrl'
                    }
                } ,
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('areadeconocimientoCreate', {
                url: '/areacreate/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathArea + '/create/areasdeconocimiento.create.html',
                        controller: 'areadeconocimientoCreateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('areadeconocimientoUpdate', {
                url: '/areaupdate/{areadeconocimientoId:int}',
                parent: 'empleados',
                param: {
                    areadeconocimientoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathArea + '/update/areasdeconocimiento.update.html',
                        controller: 'areadeconocimientoUpdateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                } 
            });
        }]);
})(window.angular);
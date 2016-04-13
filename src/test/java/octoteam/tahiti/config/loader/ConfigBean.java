package octoteam.tahiti.config.loader;

class ConfigBean {

    public FooBean foo;

    public ConfigBean() {
    }

    public ConfigBean(FooBean foo) {
        this.foo = foo;
    }

    public FooBean getFoo() {
        return foo;
    }

    public void setFoo(FooBean foo) {
        this.foo = foo;
    }

}

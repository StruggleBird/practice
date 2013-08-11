
package test._interface;

public class InterfaceTest1
{
    interface PlayAble
    {
        void play();
    }

    interface BounceAble
    {
        void play();
    }

    interface RollAble extends PlayAble, BounceAble
    {
        Ball ball = new InterfaceTest1().new Ball("football");
    }

   class Ball implements RollAble
    {
        public Ball(String name)
        {
            setName(name);
        }

        private String name;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        @Override
        public void play()
        {
            /*常量不能被重新分配值*/
            //ball = new Ball("basketball");
            System.out.println(ball.getName());
        }

    }

}

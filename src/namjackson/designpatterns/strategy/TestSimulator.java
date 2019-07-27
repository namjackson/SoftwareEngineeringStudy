package namjackson.designpatterns.strategy;

public class TestSimulator {

	public static void main(String[] args) {
		
		Weapon weapon1 = new ShotGun();
		weapon1.attack();
		weapon1.attack();
		//총알 다씀 - 동적으로 행동객체를 할당할수있다.
		weapon1.setAttackBehavior(new HitAttack());
		weapon1.attack();
		
		Weapon weapon2 = new Bow();
		weapon2.attack();
		weapon2.breakDown(); //고장 
		weapon2.attack(); //공격불가 .. 
		
		Weapon weapon3 = new Sword();
		weapon3.attack();
		weapon3.setAttackBehavior(new CutAttack());	//베기로 변경 
		weapon3.attack();
		weapon3.setAttackBehavior(new StabAttack()); //찌르기로 변
		weapon3.attack();
		
	}

}


/*
 * 직업 종류 
 * */

abstract class Weapon{
	AttackBehavior attackBehavior;
	
	public Weapon(AttackBehavior attackBehavior ){
		setAttackBehavior(attackBehavior);
	}
	
	void setAttackBehavior(AttackBehavior attackBehavior){
		this.attackBehavior=attackBehavior;	//행위 동적할당 
	}
	
	void attack(){
		attackBehavior.attack();	//attack의 행위를 행동객체에 위임! 
	}

	//부셔짐  - 동적으로 행동객체를 할당할수있다.
	void breakDown() {
		setAttackBehavior(new NoAttack());
	}
}

class ShotGun extends Weapon{
	public ShotGun() {
		super(new GunShootAttack());
	}
	void display(){
		//샷건.
	}
}
class Bow extends Weapon{
	public Bow() {
		super(new ArrowShootAttack());
	}
	void display(){
		//명궁.
	}
}
class Sword extends Weapon{
	public Sword() {
		super(new StabAttack());
	}
	void display(){
		//명검.
	}
}
class ToyGun extends Weapon{
	public ToyGun() {
		super(new NoAttack());
	}
	void display(){
		//장난감.
	}
}


/*
 * 공격 종류 
 * */

interface AttackBehavior {
	public void attack();
}
class GunShootAttack implements AttackBehavior{
	public void attack() {
		System.out.println("총 발사 ! ");
	}
}
class ArrowShootAttack implements AttackBehavior{
	public void attack() {
		System.out.println("화살 발사 ! ");
	}
}
class StabAttack implements AttackBehavior{
	public void attack() {
		System.out.println("찌르기 ! ");
	}
}
class CutAttack implements AttackBehavior{
	public void attack() {
		System.out.println("베기 ! ");
	}
}
class HitAttack implements AttackBehavior{
	public void attack() {
		System.out.println("때리기 !");
	}
}
class NoAttack implements AttackBehavior{
	public void attack() {
		System.out.println("공격 불가");
	}
}

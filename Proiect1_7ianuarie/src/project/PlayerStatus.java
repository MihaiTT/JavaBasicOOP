package project;



public class PlayerStatus {
	private String nickname;
	private int score;
	private int lives;
	private int health;
	private String weaponInHand;
	private double positionX;
	private double positionY;
	private static String gameName;
	
	//public void set_nickname(String nickname) {
		//this.nickname=nickname;
	//}
	public void set_score(int score) {
		this.score=score;
	}
	public void set_lives(int lives) {
		this.lives=lives;
		checkLives();
	}
	public void set_health(int health) {
		
		this.health=health;
		checkHealth();
	}
	// verifica conditii Health
	public void checkHealth() {
		if(get_health()<=0)
		{
			lives--;
			checkLives();
			set_health(100);
		}
		if(get_health()>100)
		{
			lives++;
			set_health(100);
		}
	}
	// verifica conditii lives
	public void checkLives() {
		if(get_lives()==0) System.out.println("Game Over");
	}
	
	//verifica daca are scor suficient pentru a cumpara arma X
	
	public boolean checkMoney(String weaponInHand) {
		switch(weaponInHand) {
		case "knife":
			if(get_score()>=1000) {
				set_score(get_score()-1000);;
				return true;
			}
			else {System.out.println("Not enough money");
					return false;
			}
			
		
		case "sniper":
			if(get_score()>=10000) {
				set_score(get_score()-10000);;
				return true;}
			else {System.out.println("Not enough money");
					return false;
			}
			
		case "kalashnikov":
			if(get_score()>=20000) {
				set_score(get_score()-20000);
				return true;
			}
			else {System.out.println("Not enough money");
					return false;
			}
			
		default:
			System.out.println("No weapon with that name");
			return false;	
		
			
		}
		
		
	}
	public void set_weapon_in_hand(String weaponInHand) {
		if(checkMoney(weaponInHand)==true)
			this.weaponInHand=weaponInHand;			
	}
	
	//distanta
	public double calculeazaDistanta(PlayerStatus p2) {
		double x;
		x=Math.sqrt(Math.pow(this.get_positionX()-p2.get_positionX() , 2 ) + Math.pow(this.get_positionY()-p2.get_positionY(),2));
		return x;
	}
	//duel
	public boolean duel(PlayerStatus p2) {
		if(this.get_weapon_in_hand().equals(p2.get_weapon_in_hand()))
		{double chanceP1;
		double chanceP2;
		chanceP1=(3 * this.get_health() + this.get_score()/1000)/4;
		chanceP2=(3 * p2.get_health() + p2.get_score()/1000)/4;
		if(chanceP1>chanceP2) {
			System.out.println("Ai castigat");
			return true;
		}
		else {
			System.out.println("Ai pierdut");
			return false;
		}
		}
		else if(calculeazaDistanta(p2)>1000 )
		{
			if(this.get_weapon_in_hand().equals("sniper")) {System.out.println("Ai castigat"); return true; }
			if(this.get_weapon_in_hand().equals("knife")) {System.out.println("Ai pierdut"); return false;}
			if(this.get_weapon_in_hand().equals("kalashnikov")) {
				if(p2.get_weapon_in_hand().equals("knife")) {System.out.println("Ai castigat"); return true;}
				else {System.out.println("Ai pierdut"); return false;}
			}
			
		}
		else {
			if(this.get_weapon_in_hand().equals("knife")) {System.out.println("Ai pierdut"); return false;}
			if(this.get_weapon_in_hand().equals("kalashnikov")) {System.out.println("Ai castigat"); return true;}
			if(this.get_weapon_in_hand().equals("sniper")) {
				if(p2.get_weapon_in_hand().equals("knife")) {System.out.println("Ai castigat"); return true;}
				else {System.out.println("Ai pierdut"); return false;}
				}
		}
		
		
		return false;
	}
	
	public void set_positionX(double positionX) {
		this.positionX=positionX;
	}
	public void set_positionY(double positionY) {
		this.positionY=positionY;
	}
	public void set_gameName(String gameName) {
		this.gameName=gameName;
	}
	public String get_nickname() {
		return this.nickname;
	}
	public int get_score() {
		return this.score;
	}
	public int get_lives() {
		return this.lives;
	}
	public int get_health() {
		return this.health;
	}
	public String get_weapon_in_hand() {
		return this.weaponInHand;
	}
	public double get_positionX() {
		return this.positionX;
	}
	public double get_positionY() {
		return this.positionY;
	}
	public String get_gameName() {
		return this.gameName;
	}
	public PlayerStatus(String nickname){
		this.nickname=nickname;
		set_score(0);
		set_lives(0);
		set_health(0);
		set_weapon_in_hand("");
		set_positionX(0);
		set_positionY(0);
	}
	public PlayerStatus(String nickname,int lives) {
		this(nickname);
		set_lives(lives);
	}
	public PlayerStatus(String nickname,int lives, int score) {
		this(nickname,lives);
		set_score(score);
	}
	public void movePlayerTo(double positionX, double positionY) {
		set_positionX(positionX);
		set_positionY(positionY);
	}
	public boolean shouldAttackOppoonent(PlayerStatus p2) {
		if(duel(p2)==true) {
			System.out.println("Ar castiga");
			return true;
		}
		else {
			System.out.println("Ar pierde");
			return false;
		}
		
	}
	public  boolean isPerfect(int nr) {
		int sumadiv=0;
		for(int i=1;i<=nr/2;i++) {
			if(nr%i==0) sumadiv+=i;
		}
		if(sumadiv==nr) return true;
		else return false;
	}
	public boolean isPrim(int nr) {
		
		for(int i=2;i<=nr/2;i++) {
			if(nr/i ==0) return false;
			
		}
		return true;
	}
	public boolean isTrap(int nr) {
		int suma=0;
		if(nr%2!=0) return false;
		else {
			while(nr!=0) {
				suma+=nr%10;
				nr/=10;
			}
			if(suma%3==0) return true;
			else return false;
		}
	}
	public void findArtifact(int artifactCode) {
		int ok=0;
		if(isPerfect(artifactCode)==true) {
			ok=1;
			set_score(get_score()+5000);
			set_lives(get_lives()+1);
			set_health(100);
		}
		if(isPrim(artifactCode)==true) {
			ok=1;
			set_score(get_score()+1000);
			set_lives(get_lives()+2);
			set_health(get_health()+25);
		}
		if(isTrap(artifactCode)==true) {
			ok=1;
			set_score(get_score()-3000);
			set_health(get_health()-25);
			
		}
		if(ok==0) set_score(get_score()+artifactCode);
		
	}
	
	
	
}
